/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt;

/**
 *
 * @author Kahol
 */
public class script
    {
        public script(String name, String date, String desc)
        {
            this.name=name;
            this.date=date;
            this.desc=desc;
        }
        String name;
        String date;
        String desc;
        public String getname()
        {
            return name;
        }
        
        public String getdate()
        {
            return date;
        }
        public String getdesc()
        {
            return desc;
        }
        
        public void setdesc(String a)
        {
            this.desc=a;
        }
    }
