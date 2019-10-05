import java.util.Stack;

import javax.swing.*;
public class Tester
{
	public static void main(String args[]){
		boolean isRunning = true;
		while(isRunning){
			String testString = JOptionPane.showInputDialog("Enter infix expression.");
			if(testString == null){
				break;
			}
			if (checkDelim(testString)){ 
				Postfix converter = new Postfix();
				String post = converter.toPost(testString);
				JOptionPane.showMessageDialog(null, post);
				JOptionPane.showMessageDialog(null, "Evaluated expression: " + converter.calculate(post));
			}
			else{
				JOptionPane.showMessageDialog(null,"Delimiters don't match.");
			}     
		} 
	}
	
	public static boolean checkDelim(String testString){
		try{
			Stack <Character> stack = new Stack<Character>();

			for(int i = 0; i < testString.length(); i++){
				char testChar = testString.charAt(i);
				if(testChar == '{' || testChar == '[' || testChar == '('){
					stack.push(testChar);
				}
				else if(testChar == '}'){
					if((char)stack.pop() != '{')
						return false;
				}
				else if(testChar == ']'){
					if((char)stack.pop() != '[')
						return false;
				}
				else if(testChar == ')'){
					if((char)stack.pop() != '(')
						return false;
				}
			}
			return stack.isEmpty();
		}
		catch(Exception e){
			return false;
		}
	}
}

