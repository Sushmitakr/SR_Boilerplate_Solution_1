package com.stackroute.datamunger;

/*There are total 5 DataMungertest files:
 * 
 * 1)DataMungerTestTask1.java file is for testing following 3 methods
 * a)getSplitStrings()  b) getFileName()  c) getBaseQuery()
 * 
 * Once you implement the above 3 methods,run DataMungerTestTask1.java
 * 
 * 2)DataMungerTestTask2.java file is for testing following 3 methods
 * a)getFields() b) getConditionsPartQuery() c) getConditions()
 * 
 * Once you implement the above 3 methods,run DataMungerTestTask2.java
 * 
 * 3)DataMungerTestTask3.java file is for testing following 2 methods
 * a)getLogicalOperators() b) getOrderByFields()
 * 
 * Once you implement the above 2 methods,run DataMungerTestTask3.java
 * 
 * 4)DataMungerTestTask4.java file is for testing following 2 methods
 * a)getGroupByFields()  b) getAggregateFunctions()
 * 
 * Once you implement the above 2 methods,run DataMungerTestTask4.java
 * 
 * Once you implement all the methods run DataMungerTest.java.This test case consist of all
 * the test cases together.
 */

public class DataMunger {

	/*
	 * This method will split the query string based on space into an array of words
	 * and display it on console
	 */

	public String[] getSplitStrings(String queryString) {
		String[] str = queryString.toLowerCase().split(" ");
		for(int i = 0; i< str.length ; i++) {
			//System.out.println(str);
		}
		
		return str;
	}

	/*
	 * Extract the name of the file from the query. File name can be found after a
	 * space after "from" clause. Note: ----- CSV file can contain a field that
	 * contains from as a part of the column name. For eg: from_date,from_hrs etc.
	 * 
	 * Please consider this while extracting the file name in this method.
	 */

	public String getFileName(String queryString) {
		//queryString = "select city,winner,team1,team2 from ipl.csv";
		
		String filename = queryString.split("from")[1].trim().split("\\s+")[0];
		
		return filename;
				/*String[] str = queryString.split(" ");
					int ctr=0;
					for(int i=0; i<str.length; i++)
					{
						//System.out.println(queryString);
						}

			for(int i = 0; i< str.length; i++) {
				
				if(str[i].contains("ipl.csv") ) {
							//	System.out.println(str[i]);
					//System.out.println("Matched");
					return "matched";
				}
				
			}
			return "ipl.csv";*/
				
	}

	/*
	 * This method is used to extract the baseQuery from the query string. BaseQuery
	 * contains from the beginning of the query till the where clause
	 * 
	 * Note: ------- 1. The query might not contain where clause but contain order
	 * by or group by clause 2. The query might not contain where, order by or group
	 * by clause 3. The query might not contain where, but can contain both group by
	 * and order by clause
	 */
	
	public String getBaseQuery(String queryString) {
		
		String baseQuery="";
	       String[] splitString= {""};
	       if( queryString.contains("group by") | queryString.contains("order by") )
	       {
	           int k=0;
	           String[] tempSplitString=getSplitStrings(queryString);
	           for(int i=0;i<tempSplitString.length-1;i++)
	           {
	               if( (tempSplitString[i].equals("group") & tempSplitString[i+1].equals("by"))|(tempSplitString[i].equals("order") & tempSplitString[i+1].equals("by")) )
	               {
	                   break;
	               }
	               else
	               {
	            	   k++;
	               }
	           }
	           splitString=new String[k];
	           k=0;
	           for(int i=0;i<tempSplitString.length-1;i++)
	           {
	               if( (tempSplitString[i].equals("group") & tempSplitString[i+1].equals("by"))|(tempSplitString[i].equals("order") & tempSplitString[i+1].equals("by")) )
	               {
	                   break;
	               }
	               else
	               {
	                   splitString[k]=tempSplitString[i];
	                   k++;
	               }
	           }
	       }
	       else
	       {
	    	   splitString=getSplitStrings(queryString);
	    	     
	       }
	       baseQuery=splitString[0]+" ";
	      for(int i=1;i<splitString.length;i++)
	      {
	           if( splitString[i-1].equals("from") )
	           {
	             baseQuery+=splitString[i];
	               break;
	           }
	           else
	           {
	               baseQuery+=splitString[i]+" ";
	           }
	      }
	       //baseQuery=queryString.trim().toLowerCase().split("where")[0];
	       return baseQuery.trim();
	    }       
		
		/*String str = "";
		if(str.contains("where")) {
			 str = queryString.split("where")[0];
			 return str;
		}else if(str.contains("order by")) {
			 str = queryString.split("order by")[0];
			return str;
		}else if(str.contains("group by")) {
			 str = queryString.split("group by")[0];
			 return str;
		}else {
			
		}
		return null;*/
		/*CharSequence s = "where";
		String string = " ";
		for(int i = 0; i<str.length; i++) {
			while(str[i].contains(s)) {
				//string strWhere = str.split("where");
				string += str[i] + "";
				break;
			}
		}
		System.out.println(string);*/
		
	

	/*
	 * This method will extract the fields to be selected from the query string. The
	 * query string can have multiple fields separated by comma. The extracted
	 * fields will be stored in a String array which is to be printed in console as
	 * well as to be returned by the method
	 * 
	 * Note: 1. The field name or value in the condition can contain keywords
	 * as a substring. For eg: from_city,job_order_no,group_no etc. 2. The field
	 * name can contain '*'
	 * 
	 */
	
	public String[] getFields(String queryString) {
		//"select city,winner,player_match from ipl.csv where season > 2014"
		// { "city", "winner", "player_match" }
		
		String [] fields= {""};
        String tempfields="";
        String[] splitString=getSplitStrings(queryString);
        for(int i=1;i<splitString.length;i++)
        {
            if(splitString[i].contains("from"))
            {
                tempfields=splitString[i-1];
                break;
            }
            
        }
        fields=tempfields.split(",");
        return fields;
		/*String str = queryString;
		String[] arr = str.split("\\s");
		 String query = arr[1].toString();
		 String[] arr1 = query.split(",");
	
		return arr1;*/
		
	}

	/*
	 * This method is used to extract the conditions part from the query string. The
	 * conditions part contains starting from where keyword till the next keyword,
	 * which is either group by or order by clause. In case of absence of both group
	 * by and order by clause, it will contain till the end of the query string.
	 * Note:  1. The field name or value in the condition can contain keywords
	 * as a substring. For eg: from_city,job_order_no,group_no etc. 2. The query
	 * might not contain where clause at all.
	 */
	
	public String getConditionsPartQuery(String queryString) {
		//"select city,winner,player_match from ipl.csv where season > 2014"
		
		if( queryString.contains("where") )
	       {
	       String conditionsPartQuery="";
	       boolean flag=false;
	      String[] splitString=getSplitStrings(queryString);
	      for( int i=0;i<splitString.length;i++)
	      {
	        if( flag )
	        {          
	            conditionsPartQuery+=" "+splitString[i];    
	        }
	           if( splitString[i].equals("where") )
	           {
	             flag=true;
	           }
	      }
	      conditionsPartQuery=conditionsPartQuery.replace("= '", "='");
	      if( conditionsPartQuery.contains("group by") )
	      {
	          conditionsPartQuery=conditionsPartQuery.substring(0, conditionsPartQuery.indexOf("group by"));
	      }
	      else if( conditionsPartQuery.contains("order by") )
	      {
	          conditionsPartQuery=conditionsPartQuery.substring(0, conditionsPartQuery.indexOf("order by"));
	      }
	       return conditionsPartQuery.trim();
	       }
	       else
	       {
	           return null;
	       }
	}
		/*int j, i = queryString.indexOf("where");
		String query = "";
		for(j = i+1; j<queryString.length();j++) {
			System.out.println(query.substring(i));
			
		}
		return query ;  */

	/*
	 * This method will extract condition(s) from the query string. The query can
	 * contain one or multiple conditions. In case of multiple conditions, the
	 * conditions will be separated by AND/OR keywords. for eg: Input: select
	 * city,winner,player_match from ipl.csv where season > 2014 and city
	 * ='Bangalore'
	 * 
	 * This method will return a string array ["season > 2014","city ='bangalore'"]
	 * and print the array
	 * 
	 * Note: ----- 1. The field name or value in the condition can contain keywords
	 * as a substring. For eg: from_city,job_order_no,group_no etc. 2. The query
	 * might not contain where clause at all.
	 */
		
	public String[] getConditions(String queryString) {

		//"select city,winner,player_match from ipl.csv where season > 2014 and city * ='Bangalore'"
		
	       
		if(queryString.contains("where")) {
            if (queryString.contains("='")) {
                queryString = queryString.replace("='", "= '");
            }
            queryString = queryString.toLowerCase();
            String string = "";
            try {
                string = queryString.split("where")[1].trim();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            int l = 0;
            String[] st1 = string.split(" ");
            for (int i = 0; i < st1.length - 1; i++) {
                if (st1[i].equals("=") | st1[i].equals(">") | st1[i].equals("<")) {
                    l++;
                }
            }
            String[] conditions = new String[l];
            l = 0;
            for (int i = 1; i < st1.length - 1; i++) {
                if (st1[i].equals("=") | st1[i].equals(">") | st1[i].equals("<")) {
                    conditions[l] = st1[i - 1].trim() + " " + st1[i].trim() + " " + st1[i + 1].trim();

                    l++;
                }
            }
            for (int i = 0; i < conditions.length; i++) {
                if (conditions[i].contains("= '")) {
                    conditions[i] = conditions[i].replace("= '", "='");
                }
            }
            for (String s : conditions) {
                System.out.println(s);
            }
            return conditions;
        }
        return null;
	}
   	
		/*int index = queryString.indexOf("where");
		String str = queryString;
		String	sub = "" ;
		String substrng1 = str.substring(index+1);
		if(substrng1.contains("and")) {
				sub = substrng1.replace("and", ",");
		}
		if(substrng1.contains("or")) {
				sub = substrng1.replace("or", ",");
		}
		String[] arr = sub.split(",");
		System.out.println(arr);

		return arr;*/
	

	/*
	 * This method will extract logical operators(AND/OR) from the query string. The
	 * extracted logical operators will be stored in a String array which will be
	 * returned by the method and the same will be printed Note:  1. AND/OR
	 * keyword will exist in the query only if where conditions exists and it
	 * contains multiple conditions. 2. AND/OR can exist as a substring in the
	 * conditions as well. For eg: name='Alexander',color='Red' etc. Please consider
	 * these as well when extracting the logical operators.
	 * 
	 */

	public String[] getLogicalOperators(String queryString) {
		//"select city,winner,player_match from ipl.csv where season > 2014 and city * ='Bangalore'"
		  
	      if(queryString.contains("and")|queryString.contains("or"))
	       {
	        String[] str=queryString.toLowerCase().split(" ");
	        int l=0;
	        for(int i=0;i<str.length;i++)
	        {
	            //System.out.println(str[i]);
	            if(str[i].equals("and") | str[i].equals("or"))
	            {
	                l++;
	            }
	            
	        }
	        String[] logical=new String[l];
	        l=0;
	        for(int i=0;i<str.length;i++)
	        {
	            //System.out.println(str[i]);
	            if(str[i].equals("and") | str[i].equals("or"))
	            {
	                logical[l]=str[i];
	                System.out.println(logical[l]);
	                l++;
	            }
	            
	        }
	        return logical;
	       }
	        else
	        {

	            return null;
	        }
	    }
	/*	String[] str = queryString.split(" ");
		String[] string = {""};
		for(int i = 0; i < str.length; i++) {
			
			if(str[i].contains("and")) {
				string[i] = str[i];
				System.out.println("andbc"+string[i]);
				return string;
				
			}else if(str[i].contains("or")) {
				string[i] = str[i];
				System.out.println("orxyz"+string[i]);
				return string;
				
			}else if(str[i].contains("and") && str[i].contains("or")) {
				string[i] = str[i];
				System.out.println("both"+string[i]);
				return string;
			}
		}*/
		
		

	/*
	 * This method extracts the order by fields from the query string. Note: 
	 * 1. The query string can contain more than one order by fields. 2. The query
	 * string might not contain order by clause at all. 3. The field names,condition
	 * values might contain "order" as a substring. For eg:order_number,job_order
	 * Consider this while extracting the order by fields
	 */

	public String[] getOrderByFields(String queryString) {
		  if( queryString.contains("order by") )
	       {
	       String[] orderByFields= {""};
	       String tempOrderByFields="";
	       String[] tempOrderByFieldsArray= {""};
	       int l=0;
	       String[] splitString=getSplitStrings(queryString);
	       for(int i=1;i<splitString.length-1;i++)
	       {
	           if( splitString[i-1].equals("order") & splitString[i].equals("by") )
	           {
	               tempOrderByFields=splitString[i+1];
	           }
	       }
	       if( tempOrderByFields.contains(",") )
	       {
	           
	           tempOrderByFieldsArray=tempOrderByFields.split(",");
	           for(int i=0;i<tempOrderByFieldsArray.length;i++)
	          {
	               
	               orderByFields[l]=tempOrderByFieldsArray[i];
	               l++;
	           }
	       }
	       else
	       {
	           orderByFields[l]=tempOrderByFields;
	       }
	       
	       for(String s:orderByFields) {
	    	   System.out.println(s);
	       }
	       return orderByFields;
	       }
	       else
	       {
	           String[] orderByFields=null;
	           return orderByFields;
	       }
	}
	/*
	 * This method extracts the group by fields from the query string. Note:
	 * 1. The query string can contain more than one group by fields. 2. The query
	 * string might not contain group by clause at all. 3. The field names,condition
	 * values might contain "group" as a substring. For eg: newsgroup_name
	 * 
	 * Consider this while extracting the group by fields
	 */

	public String[] getGroupByFields(String queryString) {
		  if( queryString.contains("group by") )
	       {
	       String[] groupByFields= {""};
	       String tempGroupByFields="";
	       String[] tempGroupByFieldsArray= {""};
	       int l=0;
	       String[] splitString=getSplitStrings(queryString);
	       for(int i=1;i<splitString.length-1;i++)
	       {
	           if( splitString[i-1].equals("group") & splitString[i].equals("by") )
	           {
	               tempGroupByFields=splitString[i+1];
	           }
	       }
	       if( tempGroupByFields.contains(",") )
	       {
	           
	           tempGroupByFieldsArray=tempGroupByFields.split(",");
	           for(int i=0;i<tempGroupByFieldsArray.length;i++)
	          {
	               
	               groupByFields[l]=tempGroupByFieldsArray[i];
	               l++;
	           }
	       }
	       else
	       {
	           groupByFields[l]=tempGroupByFields;
	       }
	       return groupByFields;
	       }
	       else
	       {
	           String[] groupByFields=null;
	           return groupByFields;
	       }
	    }


	/*
	 * This method extracts the aggregate functions from the query string. Note:
	 *  1. aggregate functions will start with "sum"/"count"/"min"/"max"/"avg"
	 * followed by "(" 2. The field names might
	 * contain"sum"/"count"/"min"/"max"/"avg" as a substring. For eg:
	 * account_number,consumed_qty,nominee_name
	 * 
	 * Consider this while extracting the aggregate functions
	 */

	public String[] getAggregateFunctions(String queryString) {

		
		String str="";
	       //System.out.println(queryString);
	       if(queryString.contains("sum(") ||
	               queryString.contains("count(") ||
	               queryString.contains("min(") ||
	               queryString.contains("max(") ||
	               queryString.contains("avg("))
	       {
	           String query=queryString.substring(queryString.indexOf("select")+7,queryString.indexOf(" from"));
	           System.out.println(query);
	           String[] splitedQuery=query.split(",");
	           String aggregate="";
	           for (int i = 0; i < splitedQuery.length; i++) {
	               //System.out.println(splitedQuery[i]) ;
	               if(splitedQuery[i].contains("sum"))
	               {
	                   aggregate+=splitedQuery[i]+" ";
	               }
	               if(splitedQuery[i].contains("count"))
	               {
	                   aggregate+=splitedQuery[i]+" ";
	               }
	               if(splitedQuery[i].contains("max"))
	               {
	                   aggregate+=splitedQuery[i]+" ";
	               }
	               if(splitedQuery[i].contains("min"))
	               {
	                   aggregate+=splitedQuery[i]+" ";
	               }
	               if(splitedQuery[i].contains("avg"))
	               {
	                   aggregate+=splitedQuery[i]+" ";
	               }
	           }
	           //System.out.println(aggregate);
	           String[] resultAggregate=aggregate.split(" ");
	           /*for (int i = 0; i < resultAggregate.length; i++) {
	               ///System.out.println(resultAggregate[i]);
	           }*/
	           return resultAggregate;          
	       }
	       else
	       {
	           return null;
	       }
	}

}