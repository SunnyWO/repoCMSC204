
public class Notation {
	
	//In the infixToPostfix method, you MUST use a queue 
	//for the internal structure that holds the postfix solution. 
	//Then use the toString method of the Queue to return the solution as a string.
	public static String convertInfixToPostfix(String infix) throws InvalidNotationFormatException {
		NotationQueue<Character> solutionQueue = new NotationQueue<Character>(infix.length());
		NotationStack<Character> processStack = new NotationStack<Character>(infix.length());

		//Read the infix expression from left to right
		for(int i=0;i<infix.length();i++) {
			//If the current character in the infix is a space, ignore it.
			if(infix.charAt(i)==(' ')) {
				continue;
			}
			//If the current character in the infix is a digit, copy it to the postfix solution queue
			else if(Character.isDigit((infix.charAt(i)))) {
				try {
					solutionQueue.enqueue(infix.charAt(i));
				} catch (QueueOverflowException e) {
					e.printStackTrace();
				}
			}
			//If the current character in the infix is a left parenthesis, push it onto the stack 
			else if(infix.charAt(i)==('(')) {
				try {
					processStack.push(infix.charAt(i));
				} catch (StackOverflowException e) {
					e.printStackTrace();
				}
			}
			//If the current character in the infix is an operator, 
			else if(infix.charAt(i)==('+')||infix.charAt(i)==('-')||infix.charAt(i)==('*')||infix.charAt(i)==('/')) {
				if(infix.charAt(i)==('(')) {
				System.out.println("how though");}
				//Pop operators (if there are any) at the top of the stack while they have 
		 		//equal or higher precedence than the current operator
				if(!processStack.isEmpty()) {
					char top=' ';
					try {
						top = processStack.top();
					} catch (StackUnderflowException e) {
						e.printStackTrace();
					}
					//if top is + or - presedance is 1 else it's 2
					int topPrecedence=0;
					if(top==('+')||top==('-')) {
						topPrecedence=1;
					}
					if(top==('*')||top==('/')) {
						topPrecedence=2;
					}
					
					char currentChar = infix.charAt(i);
					//if currentChar is + or - precedence is 1 else it's 2
					int currentPrecedence=2;
					if(currentChar==('+')||currentChar==('-')) {
						currentPrecedence=1;
					}
					
					//insert the popped operators in postfix solution queue
					if(topPrecedence>=currentPrecedence) {
						try {
							if(processStack.top()==('(')) {
								System.out.println("wrong");
							}
							solutionQueue.enqueue(processStack.pop());
						} catch (QueueOverflowException | StackUnderflowException e) {
							e.printStackTrace();
						}
					}
				}
				//Push the current character in the infix onto the stack 
				try {
					processStack.push(infix.charAt(i));
				} catch (StackOverflowException e) {
					e.printStackTrace();
				}
			}
			
			else if(infix.charAt(i)==(')')) {
				//Pop operators from the top of the stack and insert them 
				//in postfix solution queue until a left parenthesis is at 
				//the top of the stack, if no left parenthesis-throw an error
				if(processStack.isEmpty()) {
					throw new InvalidNotationFormatException();
				}

				try {
					while(processStack.top()!=('(')) {
						try {
							solutionQueue.enqueue(processStack.pop());
						} catch (QueueOverflowException | StackUnderflowException e) {
						}
						if(processStack.isEmpty()) {
							throw new InvalidNotationFormatException();
						}
					}
				} catch (StackUnderflowException e) {
					System.out.println("underflowExcep");
				}
				
				//Pop (and discard) the left parenthesis from the stack 
				try {
					processStack.pop();
				} catch (StackUnderflowException e) {
					e.printStackTrace();
				}
			}	
		}
		
		//When the infix expression has been read, 
		//Pop any remaining operators and insert them in postfix solution queue.
		while(!processStack.isEmpty()) {
			try {
				solutionQueue.enqueue(processStack.pop());
			} catch (QueueOverflowException | StackUnderflowException e) {
				e.printStackTrace();
			}
		}
		
		String postfix="";
		while(!solutionQueue.isEmpty()) {
			try {
				postfix+=solutionQueue.dequeue();
			} catch (QueueUnderflowException e) {
				e.printStackTrace();
			}
		}
		
		return postfix;
	}
	
	public static String convertPostfixToInfix(String postfix) throws InvalidNotationFormatException {
		NotationQueue<Character> solutionQueue = new NotationQueue<Character>(postfix.length());
		NotationStack<String> processStack = new NotationStack<String>(postfix.length());

		//Read the postfix expression from left to right
		for(int i=0;i<postfix.length();i++) {
			//If the current character in the postfix is a space, ignore it.
			if(postfix.charAt(i)==(' ')) {
				continue;
			}
			
			//If the current character is an operand, push it on the stack
			else if(Character.isDigit((postfix.charAt(i)))) {
				try {
					processStack.push(Character.toString(postfix.charAt(i)));
				} catch (StackOverflowException e) {
					e.printStackTrace();
				}
			}
			
			if(postfix.charAt(i)==('+')||postfix.charAt(i)==('-')||postfix.charAt(i)==('*')||postfix.charAt(i)==('/')) {
				//Pop the top 2 values from the stack. If there are fewer than 2 values throw an error
				String firstValue="",secondValue="";
				if(processStack.isEmpty()) {
					throw new InvalidNotationFormatException();
				}
				try {
					secondValue = processStack.pop();
				} catch (StackUnderflowException e) {
					e.printStackTrace();
				}
				if(processStack.isEmpty()) {
					throw new InvalidNotationFormatException();
				}
				try {
					firstValue = processStack.pop();
				} catch (StackUnderflowException e) {
					e.printStackTrace();
				}
				
				//Create a string with 1st value and then the operator and then the 2nd value.
				//Encapsulate the resulting string within parenthesis
				String pushString = "("+firstValue+postfix.charAt(i)+secondValue+")";

				
				//Push the resulting string back to the stack
				try {
					processStack.push(pushString);
				} catch (StackOverflowException e) {
					e.printStackTrace();
				}
			}
			
			
			
			
		}
		if(processStack.size()==1) {
			try {
				return processStack.pop();
			} catch (StackUnderflowException e) {
				e.printStackTrace();
			}
		}
		else {
			throw new InvalidNotationFormatException();
		}
		return postfix;

	}
	
	public static double evaluatePostfixExpression(String postfixExpr) throws InvalidNotationFormatException {
		NotationStack<String> processStack = new NotationStack<String>(postfixExpr.length());

		//Read the postfix expression from left to right
		for(int i=0;i<postfixExpr.length();i++) {
			//If the current character in the postfix is a space, ignore it.
			if(postfixExpr.charAt(i)==(' ')) {
				continue;
			}
			
			//If the current character in the postfix is a left parenthesis, push it onto the stack 
			else if(postfixExpr.charAt(i)==('(')||Character.isDigit(postfixExpr.charAt(i))) {
				try {
					processStack.push(Character.toString(postfixExpr.charAt(i)));
				} catch (StackOverflowException e) {
					e.printStackTrace();
				}
			}
			
			if(postfixExpr.charAt(i)==('+')||postfixExpr.charAt(i)==('-')||postfixExpr.charAt(i)==('*')||postfixExpr.charAt(i)==('/')) {
				//Pop the top 2 values from the stack. If there are fewer than 2 values throw an error
				String firstValue="",secondValue="";
				if(processStack.isEmpty()) {
					System.out.println("second");
					throw new InvalidNotationFormatException();
				}
				try {
					secondValue = processStack.pop();
				} catch (StackUnderflowException e) {
					e.printStackTrace();
				}
				if(processStack.isEmpty()) {
					System.out.println("first");
					throw new InvalidNotationFormatException();
				}
				try {
					firstValue = processStack.pop();
				} catch (StackUnderflowException e) {
					e.printStackTrace();
				}
				
				//Create a string with 1st value and then the operator and then the 2nd value.
				//Encapsulate the resulting string within parenthesis
				double result=0;
				if(postfixExpr.charAt(i)==('+')) {
					result = Double.parseDouble(firstValue) + Double.parseDouble(secondValue);
				}
				if(postfixExpr.charAt(i)==('-')) {
					result = Double.parseDouble(firstValue) - Double.parseDouble(secondValue);
				}
				if(postfixExpr.charAt(i)==('*')) {
					result = Double.parseDouble(firstValue) * Double.parseDouble(secondValue);
				}
				if(postfixExpr.charAt(i)==('/')) {
					result = Double.parseDouble(firstValue) / Double.parseDouble(secondValue);
				}
				String pushString = Double.toString(result);

				//Push the resulting string back to the stack
				try {
					processStack.push(pushString);
				} catch (StackOverflowException e) {
					e.printStackTrace();
				}
			}
			
		}
		if(processStack.size()==1) {
			try {
				return Double.parseDouble(processStack.pop());
			} catch (StackUnderflowException e) {
				e.printStackTrace();
			}
		}
		else {
			
			throw new InvalidNotationFormatException();
		}
		return 0;
	}
	
	public static double evaluateInfixExpression(String infixExpr) throws InvalidNotationFormatException {
		return evaluatePostfixExpression(convertInfixToPostfix(infixExpr));
	}

}
