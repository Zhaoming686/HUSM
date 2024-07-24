
package temp;

import java.util.Timer;
import java.util.TimerTask;

public class Reminder {
    Timer timer;
    application  app;

    public Reminder(int seconds) {
    	
    	app = new application();
        app.start();
        timer = new Timer(true);
        timer.schedule(new RemindTask(), seconds*1000);
        
        
	}

    class RemindTask extends TimerTask {
        public void run() {
            System.out.format("Time's up!%n");
            
            System.out.println(app.isAlive());
            app.stopIT();
            System.out.println(app.isAlive());
            //timer.cancel();
        }
    }
    class application extends Thread  {
    	volatile boolean stop=false; 
        public void run() {
            
            int counter=0;
            //x=1;
            while (!stop) 
            {
				//System.out.println(counter++);
			}
            System.out.println("stopped");
        }
        
        public void stopIT()
        {
        	stop=true;
        }
        
    }
    
    

    public static void main(String args[]) {
        new Reminder(7);
        System.out.format("Task scheduled.%n");
    }
}