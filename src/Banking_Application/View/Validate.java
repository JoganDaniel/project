package Banking_Application.View;

public class Validate {
    public static String z="----------------------------------".repeat(3);
    public boolean Mobilenumber(String string) {
        if(string == null || string.equals("")) {
            System.out.print(z+"\nRequired information....\nEnter your mobile number here: ");
            return false;
        }
        try {
            Long.parseLong(string);
            if(string.length()==10 && string.charAt(0)!='0'){
                return true;}
            else{
                System.out.println("Enter correct mobile number\n"+z);
                return false;}
         } catch (NumberFormatException e) {
            System.out.println("Enter correct number with 10 digits!!!!!\n"+z);
        }
        return false;
    }

    public boolean Name(String n)
    {
	 if(n == null || n.equals("")) {
        System.out.println(z+"\nRequired information....\nEnter your Name here: ");
        return false;
        }
	   char ar[]=n.toCharArray();
	   for(char i:ar)
	    {   
		if(Character.isAlphabetic(i) || i==' ') {
			continue;}
		else {
			System.out.println(z+"\nEnter correct value without numbers and special characters......\n"+z);
			return false;
		}
		}
	return true;
    }

    public boolean Aadhar(String string) {
        if(string == null || string.equals("")) {
            System.out.println(z+"Required information....\nEnter your aadhar number here: ");
            return false;
        }
        
        try {
            Long.parseLong(string);
            if(string.length()==12){
                return true;}
            else{
                System.out.println("Enter correct Aadhar number\n"+z);
                return false;}
        } catch (NumberFormatException e) {
            System.out.println(z+"Please enter 12 digit aadhar number"+z);
        }
        return false;
    }

    public boolean Pan(String n)
    {
	 if(n == null || n.equals("")) {
        System.out.println(z+"\nRequired information....\nEnter your PAN here: ");
        return false;
        }
        if(n.length()!=10){
            System.out.println(z+"\nEnter correct information: ");
            return false;
        }
	   char ar[]=n.toCharArray();
	   for(int i=0;i<5;i++)
	    {   
            if(Character.isAlphabetic(ar[i])){
                continue;
            }
            else{
                System.out.println(z+"\nWrong PAN entered. Enter correct value here:");
                return false;
            }
		}
        for(int i=5;i<9;i++)
	    {   
            if(Character.isDigit(ar[i])){
                continue;
            }
            else{
                System.out.println(z+"\nWrong PAN entered. Enter correct value here:");
                return false;
            }
		}
        if(Character.isAlphabetic(ar[9])){
	        return true;
        }
        else{
            System.out.println(z+"\nWrong PAN entered. Enter correct value here:");
            return false;
        }
    }
    public boolean Username(String string)
    {
        if(string.contains(" ") || string.length()<5){
            return false;
        }
        return true;
    }

    public boolean Password(String string)
    {
            int x=0,y=0,zz=0;
			char ch[]=string.toCharArray();
			for(char i:ch)
			{
				if(Character.isLowerCase(i) || Character.isWhitespace(i))
					continue;
				else if(Character.isUpperCase(i))
					x+=1;
				else if(Character.isDigit(i))
					y+=1;
				else
					zz+=1;
			}
			if(x>0 && y>0 && zz>0 && ch.length>=8) {
				return true;
			}
            System.out.println(z+"\nPassword should be of 8 characters with atleast one \n\t*uppercase alphabet\n\t*number\n\t*special character");
			return false;
    }

    public boolean choicecheck(String string) {
        if(string == null || string.equals("")) {
            //System.out.print(z+"Enter an input");
            return false;
        }
        try {
            Integer.parseInt(string);
            return true;
         } catch (NumberFormatException e) {
            System.out.println("Enter a number"+z);
        }
        return false;
    }
}
