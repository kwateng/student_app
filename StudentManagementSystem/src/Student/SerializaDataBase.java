/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Student;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author Kavindya
 */
public class SerializaDataBase {
public static final String x="DataBase";

    public void Serialize(DataBase ex)
    {
      try
      {
         FileOutputStream fileOut =new FileOutputStream(x);
         ObjectOutputStream out = new ObjectOutputStream(fileOut);
         out.writeObject(ex);
         out.close();
          fileOut.close();
      }catch(IOException i){}
   }

    public DataBase getDeSerialize()
    {
         DataBase check=null;
         File f1=new File(x);
         if(f1.exists()){
         try
         {
            FileInputStream fileIn = new FileInputStream(x);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            check = (DataBase) in.readObject();
            in.close();
            fileIn.close();
        }catch(IOException i)
        {}catch(ClassNotFoundException c)
        {}
         return check;
        }
        else
         {
             return null;
        }
    }
}
