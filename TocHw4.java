
import java.net.URL;
import java.io.*;
public class TocHw4 {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException, NumberFormatException{
	       
	     // URL
		if(args.length < 1)
		{
			System.out.println("No input Or input Error");
			return ;
		}
		String net = args[0];
		URL url = new URL(net);
		BufferedReader in = new BufferedReader(  new InputStreamReader(url.openStream(),"UTF-8"));
		//System.out.println(args[1]+" "+args[2]+"a"+args[3]+"a");
		//java.io.InputStream gg = System.in;
		//System.out.println(inputtown + " " + inputroad + " " + (inputyear+1) );
		String[] roadname = new String[2000];
		String[] year = new String [2000];
		int[] maxprice = new int[2000];
		int[] minprice = new int[2000];
		
		
		boolean[] isexist = new boolean[2000];
		for(int i = 0 ; i < 2000 ; i ++)
		{
			roadname[i] = null;
			maxprice[i] = 0 ;
			minprice[i] = 999999999 ;
			
			isexist[i] = false ;
		}
		
		
		String inputLine;
		
		int max = 0;
	    while ((inputLine = in.readLine()) != null)
	    {
	    	//System.out.println(new String(inputLine.getBytes("UTF-8"),"UTF-8"));
	    	
	    	//String the_town = findtown(inputLine);
	    	String the_yearstr = findyear(inputLine);
	    	String the_road = findroad(inputLine);
	    	String the_pricestr = findprice(inputLine);
	    	
	    	String name ;
	    	if(inputLine.length()>2 && the_pricestr!=null  && the_yearstr!= null && the_road != null)
	    	{	
	    		int the_price = Integer.parseInt(the_pricestr);
	    		name = cutroad(the_road);
	    		//System.out.println(the_road);
	    		//the_yearstr = the_yearstr.substring(3);
	    		int judge;
	    		judge = the_yearstr.indexOf("9");
	    		if(judge == 0)
	    		{
	    			the_yearstr = "1" + the_yearstr;
	    		}
	    		//System.out.println(name + " " + the_yearstr);
			    //System.out.println(the_town + " " + the_year + " " +the_road);
	    		//int pp = 0 ;
	    		if(name != null ){
	        	for(int ii = 0 ; ii < 2000 ; ii++)
	        	{
	        		//System.out.println(name + " "+ roadname[ii]);
	        		if(isexist[ii]==true)
	        		{
	        			if(name.equals(roadname[ii]))
	        			{
	        				if(!year[ii].contains(the_yearstr))
	        					year[ii] = year[ii] + the_yearstr;
	        				if(max < year[ii].length())
	        					max = year[ii].length();
	        				//System.out.println("FUCK~~~~~~"+pp++);
	        				if(the_price > maxprice[ii])
		        			{
		        				maxprice[ii] = the_price;
		        			}
		        			if(the_price < minprice[ii])
		        			{
		        				minprice[ii] = the_price;
		        			}
		        			
		        			
		        			break;
	        			}
	        		}
	        		else
	        		{
	        			//System.out.println("YOOO   "+ii);
	        			//int ggg ; ggg = gg.read(); 
	        			isexist[ii] = true ;
	        			roadname[ii] = name ; 
	        			year[ii] = the_yearstr;
	        			if(the_price > maxprice[ii])
	        			{
	        				maxprice[ii] = the_price;
	        			}
	        			if(the_price < minprice[ii])
	        			{
	        				minprice[ii] = the_price;
	        			}
	        			
	        			break;
	        		}
	        				
	        	}
	        	
	    		}
	    		
	    		
	    	}
	    	
	    		
	    	
	    }
	    
	    in.close();
	    for(int p = 0 ; p < 2000 ; p++)
	    {
	    	if(!isexist[p])
	    		break;
	    	else
	    	{
	    		if(year[p].length() == max )
	    			System.out.println(roadname[p] +", 最高成交價: " + maxprice[p] +", 最低成交價: "  + minprice[p]);
	    	}
	    }
	    
	   
	    
	    //System.out.println("Finish");
		}
		static String cutroad(String before)
		{
			String After = null;
			int road;
			int street;
			int bigroad;
			int lane;
			road = before.indexOf("路");
			street = before.indexOf("街");
			bigroad = before.indexOf("大道");
			lane = before.indexOf("巷");
			if(road != -1)
			{
				After = before.substring(0, road+1);
			}
			else if (street!=-1)
			{
				After = before.substring(0, street+1);
			}
			else if (bigroad!=-1)
			{
				After = before.substring(0, bigroad+2);
			}
			else if(lane != -1)
			{
				After = before.substring(0, lane+1);
			}
			
			
			return After ;
		}
		static String findtown(String line) throws IOException
		{
			
			String all[] = line.split("\"|:|,|}");
			int choose = 0;
			for(int i = 0 ; i < all.length ; i++)
			{
				
				if(choose == 1 && all[i].length() > 1)
				{
					return all[i];
					
				}
				if("鄉鎮市區".equals(all[i]))
				{
					choose = 1 ;
				}
				
			}
			return null;
			
		}
		static String findyear(String line)
		{
			
			String all[] = line.split("\"|:|,|}");
			int choose = 0;
			for(int i = 0 ; i < all.length ; i++)
			{
				
				if(choose == 1 && all[i].length() > 1)
				{
					return all[i];
					
				}
				if("交易年月".equals(all[i]))
				{
					choose = 1 ;
				}
				
			}
			return null;
			
		}
		static String findroad(String line)
		{
			
			String all[] = line.split("\"|:|,|}");
			int choose = 0;
			for(int i = 0 ; i < all.length ; i++)
			{
				
				if(choose == 1 && all[i].length() > 1)
				{
					return all[i];
					
				}
				if("土地區段位置或建物區門牌".equals(all[i]))
				{
					choose = 1 ;
				}
				
			}
			return null;
			
		}
		static String findprice(String line) throws IOException
		{
			
			String all[] = line.split("\"|:|,|}");
			int choose = 0;
			for(int i = 0 ; i < all.length ; i++)
			{
				
				if(choose == 1 && all[i].length() > 1)
				{
					return all[i];
					
				}
				if("總價元".equals(all[i]))
				{
					choose = 1 ;
				}
				
			}
			return null;
			
		}

}
