package CompactPattern;

import dataStructures.StaticData;
import dataStructures.freqComparator;
import dataStructures.myNode;
import utilities.CombinationGenerator;
import utilities.Settings;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.ParseException;
import java.util.*;
import java.util.List;

public class CompactMaxin {
    public static Map<List<String>, Integer> CodeSize = new HashMap<>();//记录每一个code所对应的图的大小
    public static void main(String[] args) throws Exception {
        String Location = " ";
        String name = "Output+" + "_" + Settings.fileName2 + "_" + Settings.utilityThre + "_" + Settings.MVC + ".txt";
        String filename = Location + name;
        Map<Integer, List<String>> GraphDFScode = ConstructDFScode(filename);
        int CompactNumber = CompactSubgraphTable(GraphDFScode);
        System.out.println("The number of Pattern is: " + GraphDFScode.size());
        System.out.println("The number of compact pattern is: " + CompactNumber);
    }
    public static Map<Integer, List<String>> ConstructDFScode(String fileName) throws Exception
    {
        String text = "";
        final BufferedReader rows = new BufferedReader(new FileReader(new File(fileName)));

        int numberOfNodes=0;

        String tempLine = null;//a temporary variable to store the last read node line

        String line;
        line = rows.readLine();
        final String[] part = line.split("\\s+");
        int number = Integer.parseInt(part[0]);
        Map<Integer, List<String>> GraphDFScode = new HashMap<>();
        line = rows.readLine();

        for (int num = 0; num < number ; num++){
            final String[] partGraphId = line.split("\\s+");
            int GraphId = Integer.parseInt(partGraphId[0]);
            if (GraphId == num){
                int Size = 0;
                List<String> DFScode = new ArrayList<>();
                String code = null;
                Map<Integer, Integer> VertexLabel = new HashMap<>();
                while ((line = rows.readLine()) !=null && (line.charAt(0) == 'v')) {
                    final String[] parts = line.split("\\s+");
                    final int index = Integer.parseInt(parts[1]);
                    final int label = Integer.parseInt(parts[2]);
                    VertexLabel.put(index, label);
                }
                while (line !=null && (line.charAt(0) == 'e')) {
                    final String[] parts = line.split("\\s+");
                    final int index1 = Integer.parseInt(parts[1]);
                    final int index2 = Integer.parseInt(parts[2]);
                    final int label = Integer.parseInt(parts[3]);
                    code = index1 + "_" + index2 + "_" + VertexLabel.get(index1) + "_" + label + "_" + VertexLabel.get(index2);
                    DFScode.add(code);
                    Size++;
                    line = rows.readLine();
                }
                GraphDFScode.put(num,DFScode);
                CodeSize.put(DFScode,Size);
            }
            else {
                continue;
            }
        }
        rows.close();
        return GraphDFScode;
    }

    public static int CompactSubgraphTable(Map<Integer, List<String>> GraphDFScode){
        //sort
        Map<Integer, List<Integer>> SizeGraphList = new TreeMap<>();
        for (int graphId : GraphDFScode.keySet()){
            int size = GraphDFScode.get(graphId).size();
            if (SizeGraphList.containsKey(size)){
                SizeGraphList.get(size).add(graphId);
            }else{
                List<Integer> GraphIdsWithSameSize = new ArrayList<>();
                GraphIdsWithSameSize.add(graphId);
                SizeGraphList.put(size, GraphIdsWithSameSize);
            }
        }
        //construct
        Map<List<String>,List<List<String>>> CompactSubTable  = new HashMap<>();
        int count = 0;
        for (int size : SizeGraphList.keySet()){
            count++;
            if (count==1){//size may not equal to 1, so we use a count
                for (int ancestorId : SizeGraphList.get(size)) {
                    List<List<String>> DesecndantsCode = new ArrayList<>();
                    CompactSubTable.put(GraphDFScode.get(ancestorId),DesecndantsCode);
                }
            }else{
                for (int desecendantId : SizeGraphList.get(size)){//遍历下一个size
                    boolean AncestorExist = false;
                    for (List<String> ancestorCode : CompactSubTable.keySet()){//遍历table的每一行
                        if (IsPrefix(ancestorCode, GraphDFScode.get(desecendantId))){//若祖先是前缀
                            AncestorExist = true;
                            boolean flagReplace = false;
                            //case1
                            if (CompactSubTable.get(ancestorCode).isEmpty()){//若是空的直接加
                                CompactSubTable.get(ancestorCode).add(GraphDFScode.get(desecendantId));
                                break;
                            }
                            //case2
                            List<String> DesecndantsCodesToRemove = new ArrayList<>();
                            for (List<String> DesecndantsCodes : CompactSubTable.get(ancestorCode)){
                                if (IsPrefix(DesecndantsCodes,GraphDFScode.get(desecendantId))){//若该祖先子列中有前缀
                                    flagReplace = true;
                                    DesecndantsCodesToRemove.addAll(DesecndantsCodes);
                                    break;
                                }
                            }
                            if (flagReplace){//祖先子列中有前缀，则替换
                                CompactSubTable.get(ancestorCode).remove(DesecndantsCodesToRemove);//删除
                                CompactSubTable.get(ancestorCode).add(GraphDFScode.get(desecendantId));//替换
                                break;
                            }else{//祖先子列中无前缀，则直接加到这个祖先子列
                                CompactSubTable.get(ancestorCode).add(GraphDFScode.get(desecendantId));
                                break;
                            }
                        }
                    }
                    if (!AncestorExist){//若没有祖先
                        List<List<String>> DesecndantsCode = new ArrayList<>();
                        CompactSubTable.put(GraphDFScode.get(desecendantId),DesecndantsCode);
                    }
                }
            }
        }
        int ToReduce = Compactmore(CompactSubTable);
        int CompaceNumber = 0;
        for (List<String> AncestorCode : CompactSubTable.keySet()){
            CompaceNumber += CompactSubTable.get(AncestorCode).size() + 1;
        }
        return CompaceNumber - ToReduce;
    }

    public static int Compactmore(Map<List<String>,List<List<String>>> CompactSubTable){
        int ToReduce = 0;
        for (List<String> ancestor : CompactSubTable.keySet()){
            int SizeAncestor = CodeSize.get(ancestor);
            int NumberChildren = CompactSubTable.get(ancestor).size();
            if (SizeAncestor == 1 && NumberChildren != 0){
//                List<String> strings = CompactSubTable.get(ancestor).get(0);
//                if (CodeSize.get(strings) == 2){
//                    ToReduce++;
//                }
                ToReduce++;
            }
        }
        return ToReduce;
    }

    public static Boolean IsPrefix(List<String> DFScodeAncestor, List<String> DFScodeDescendant){
        if (DFScodeAncestor.size()>=DFScodeDescendant.size()){
            return false;
        }
        int PrefixCount = 0;
        for (int i = 0; i < DFScodeAncestor.size(); i++){
            if (DFScodeAncestor.get(i).equals(DFScodeDescendant.get(i))){
                PrefixCount++;
                continue;
            }else{
                break;
            }
        }
        if (PrefixCount==DFScodeAncestor.size()) return true;
        return false;
    }
}
