import java.awt.event.ActionListener;

import java.io.File;

import javax.swing.Timer;

public class Racer
   implements Runnable
{
   private int mFileNumber;
   
   private FileToucher mMonitor;
   
   public Racer()
   {
      mFileNumber = 0;
      
      mMonitor = new OutputtingFileToucher();
   }
   
   public File newFileN(int number)
   {
      return new File(String.format("file.%05d", number));
   }
   
   public void run()
   {
      new Timer(1000, new ActionListener
      {
         mMonitor.run();
      });
      
      (new Thread(new CreatorRunnable())).start();
      
      Thread.sleep(2000);
      
      (new Thread(new DestroyerRunnable())).start();
   }
   
   class CreatorRunnable implements Runnable
   {
      public void run()
      {
         while (true)
         {
            try
            {
               if (newFileN(mFileNumber).createNewFile())
               {
                  mFileNumber++;
               }
               Thread.sleep(500);
            }
            catch (Exception e)
            {
            }
         }
      }
   }
   
   class DestroyerRunnable implements Runnable
   {
      public void run()
      {
         while (true)
         {
            try
            {
                if (newFileN(mFileNumber).delete())
                {
                    mFileNumber++;
                }
                Thread.sleep(500);
            }
            catch (Exception e)
            {
            }
         }
      }
   }
   
   public static void main(String[] args)
   {
      new Racer().run();
   }
}