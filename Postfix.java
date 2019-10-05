import java.util.*;
public class Postfix
{	

	public Postfix() {	
	}
	public String toPost(String input){
		input = input.replaceAll("\\s", "");
		char [] in = input.toCharArray();
		Stack <Character> stack = new Stack<Character>();
		String post = "";
		for(char c : in){
			if(Character.isLetter(c) || Character.isDigit(c)){
				post+=c;
			}
			else if(c == '('){
				stack.push(c);
			}
			else if(c == ')'){
				while(!stack.isEmpty()){
					char x = stack.pop();
					if(x!= '('){
						post += x;
					}
					else{
						break;
					}
				}
			}
			else if(c == '*' || c == '/'  || c == '+' || c == '-'){
				post += " ";
				while(!stack.isEmpty() && stack.peek() != '(' && !checkHigherPrec(stack.peek(),c))
					post += stack.pop();
				stack.push(c);
			}
		}
		while(!stack.isEmpty()){
			post += stack.pop();
		}
		return post;
	}

	private boolean checkHigherPrec(char op1, char op2){
		int one = 0;
		int two = 0;
		switch (op1) {
		case '+':
		case '-':
			one = 0;
			break;
		case '*':
		case '/':
			one =  1;
			break;
		}
		switch (op2) {
		case '+':
		case '-':
			two = 0;
			break;
		case '*':
		case '/':
			two =  1;
			break;
		}
		return one < two;
	}
	public Integer calculate(String post) {	
		//create a stack 
		Stack<Integer> stack = new Stack<>(); 

		// Scan all characters one by one 
		for(int i = 0; i < post.length(); i++) 
		{ 
			char c = post.charAt(i); 

			if(c == ' ') 
				continue; 

			// If the scanned character is an operand  
			// (number here),extract the number 
			// Push it to the stack. 
			else if(Character.isDigit(c)) 
			{ 
				int n = 0; 

				//extract the characters and store it in num 
				while(Character.isDigit(c)) 
				{ 
					n = n*10 + (int)(c-'0'); 
					i++; 
					c = post.charAt(i); 
				} 
				i--; 

				//push the number in stack 
				stack.push(n); 
			} 

			// If the scanned character is an operator, pop two 
			// elements from stack apply the operator 
			else{ 
				int val1 = stack.pop(); 
				int val2 = stack.pop();     
				switch(c){ 
				case '+': 
					stack.push(val2+val1); 
					break; 

				case '-': 
					stack.push(val2- val1); 
					break; 

				case '/': 
					stack.push(val2/val1); 
					break; 

				case '*': 
					stack.push(val2*val1); 
					break; 
				} 
			} 
		} 
		return stack.pop();  
	}
}