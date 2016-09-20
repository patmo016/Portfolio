package week09;

import java.io.*;
import java.util.Scanner;
import java.util.Stack;
import java.util.ArrayList;
import java.util.EmptyStackException;

/**RNPApp- an app that calculates aritmetic expressions as input from a user.
 *
 *@author Molly Patterson
 *@author Ben Sherwood
 */

public class RPNApp{

    /** a final array of operands.*/
    public static final String[] OPERATORS ={ "+", "-", "*", "/", "%"};
    /** a final array of repeating operands.*/
    public static final String[] R_OPERATORS={ "+!", "*!", "-!", "/!", "%!"};
    /** a final array of special operands.*/
    public static final String[] S_OPERATORS={ "d", "c", "r"};
    /**a public stack that contains a Stack of Long values.*/
    public static Stack<Long> stack;
  
  
    /**The main method that takes input from the user.
     *
     *Prints out the result to the evaluate method that takes the
     *input as an array of tokens
     *@param args not required 
     */
    public static void main (String [] args){
       
        Scanner scan = new Scanner(System.in);
        String[] tokens = new String[10];
    
        while(scan.hasNext()){
            stack = new Stack<Long>();
            String input  = scan.nextLine();
            tokens = input.split(" ");
            ArrayList<Long> isNull = evaluate(tokens, stack);
            if(isNull!=(null)){
                System.out.println (isNull);
            }
        }
        
    
    }
    
    /**Evaluates the user input.
       
     *@param tokens a String Array of tokens
     *@param stack a stack is passed (empty or not)
     *@return an Arraylist<Long> of the evaluated numbers
     *
     */
    
  
    public static ArrayList<Long> evaluate (String [] tokens,
                                            Stack<Long> stack){
        ArrayList<Long> result = new ArrayList<Long>();
        for (int i = 0; i < tokens.length; i++){
            if(isParentheses(tokens[i])){
                if(tokens[i].equals(")")){
                    System.out.println("Error: unmatched parentheses");
                    return null;
                }
     
                                       
                return(parentheses(stack, tokens));
                
            }else if (isRepeatOperator(tokens[i])){
                try{
                    while (stack.size()>1){
                        Long r= operation(stack.pop(), stack.pop(), tokens[i]);
                        stack.push(r);
                    }
                }catch (EmptyStackException e){
                    System.out.println("Error: too few operands");
                    return null;
                }      
            }else if(isSpecialOperator(tokens[i])){
                try{
                    if(stack.peek() < 0){
                        System.out.println("Error: negative copy");
                        return null;
                    }
                    stack = (specialOperation(stack, tokens[i]));
                }catch(EmptyStackException e){
                    System.out.println("Error: too few operands");
                    return null;
                }
                    
            }else if(tokens[i].equals("o")){
                try{
                    System.out.print(stack.peek()+ " ");
                }catch(EmptyStackException e){
                    System.out.println("Error: too few operands");
                    return null;
                }
               
            }else if (isOperator(tokens[i])){
                try{
                    Long oper = operation(stack.pop(), stack.pop(), tokens[i]);
                    if(oper != null){
                        stack.push(oper);
                    }else{
                        return null;
                    }
                } catch (EmptyStackException e){
                    System.out.println("Error: too few operands");
                    return null;
                }
            }else{
                try{
                    stack.push(Long.parseLong(tokens[i]));
                }catch (NumberFormatException e){
                    System.out.println("Error: bad token "+"'"+ tokens[i]+"'");
                    return null;
                }
            }
        }
        while(!stack.isEmpty()){
            result.add(0, stack.pop());
        }
        return result;
    }

    /**Detirmines if an input token is a parentheses value.
     *
     *@param token a string that represents a String value from the tokens
     *array
     *
     *@return a boolean value to detirmine if parentheses
     */
    
    public static boolean isParentheses(String token){
        if(token.equals("(")|| token.equals(")")){
            return true;
        }
        return false;
    }
                    
    /**Detirmines if an input token is one of the special operators.
     *
     *@param token a string value from the tokens Array
     *
     *@return a boolean value to say if it is a special operator
     */
    public static boolean isSpecialOperator(String token){
        for(String op: S_OPERATORS){
            if(op.equals(token)){
                return true;
            }
        }
        return false;
    }


    /**Detirmines if an input is a repeat operator (includes ! in token).
     *
     *@param token a string value from the tokens Array
     *
     *@return a boolean value detirmining if token includes the ! symbol
     */
    public static boolean isRepeatOperator(String token){
        for(String op: R_OPERATORS){
            if(op.equals(token)){
                return true;
            }
        }
        return false;
    }
        
    /**Detirmines if an input is a standard operator.
     *
     *@param token a string value from the tokens Array
     *
     *@return a boolean value detirmining if the token is an operator
     */ 
    public static boolean isOperator(String token){
        for(String op: OPERATORS){
            if(op.equals(token)){
                return true;
            }
        }
        return false;
    }
    
    /** Deals with inputs with parentheses values.
     *
     *@param stack the current stack value
     *@param tokens the complete String array of tokens from user input
     *
     *@return an array list that is the result of the sum
     */

    public static ArrayList<Long> parentheses(Stack<Long> stack,
                                              String[] tokens){
        ArrayList<Long> result = new ArrayList<Long>();
        String builder = "";
        for(String s: tokens){
            builder += s + " ";
        }
        String operandString = builder.substring(builder.indexOf("(")
                                                 + 2);
        if(operandString.indexOf(")") == -1){
            System.out.println("Error: unmatched parentheses");
            return null;
        }
        String operands = operandString.substring(0,
                                                  operandString.indexOf(")"));
        tokens = operands.split(" ");
        Long x = stack.pop();

        while(x>0){
            for(int i=0; i< tokens.length; i++){
                if (isRepeatOperator(tokens[i])){
                    try{
                        while (stack.size()>1){
                            Long r= operation(stack.pop(), stack.pop(),
                                              tokens[i]);
                            stack.push(r);
                        }
                    } catch (EmptyStackException e){
                        System.out.println("Error: too few operands");
                        return null;
                    }      
                }else if(isSpecialOperator(tokens[i])){
                    try{
                        stack = (specialOperation(stack, tokens[i]));
                    }catch(EmptyStackException e){
                        System.out.println("Error: too few operands");
                        return null;
                    }
                }else if(tokens[i].equals("o")){
                    System.out.print(stack.peek()+ " ");
                }else if (isOperator(tokens[i])){
                    try{
                        stack.push(operation(stack.pop(), stack.pop(),
                                             tokens[i]));
                    } catch (EmptyStackException e){
                        System.out.println("Error: too few operands");
                        return null;
                    }
                }else{
                    try{
                        stack.push(Long.parseLong(tokens[i]));
                    } catch (NumberFormatException e){
                        System.out.println("Error: bad token "+"'"+
                                           tokens[i]+"'");
                        return null;
                    }
                }
            }
            
            x--;
        }     
        while(!stack.isEmpty()){
            result.add(0, stack.pop());
        }
        return result;
                                     
    }

    /**a Method that deals with speacial operations.
     *
     *@param stack a Stack<Long> that holds the current stack values
     *@param token that is the speacial operation value
     *
     *@return a Stack<Long> that is the stack with its new values
     */
    public static Stack<Long> specialOperation(Stack<Long> stack, String token){
        if(token.equals("d")){
            try{
                stack.push(stack.peek());
            }catch(EmptyStackException e){
                System.out.println("Error: too few operands");
                return null;
            }
            return stack;
        }else if(token.equals("c")){
            try{
                Long y= stack.pop();
                Long x= stack.pop();
                
                while (y>0){
                    stack.push(x);
                    y--;
                }
            }catch (EmptyStackException e){
                System.out.println("Error: too few operands");
                return null;
            }
            return stack;
        }else if(token.equals("r")){
            Stack <Long> temp = new Stack<Long>();
            Long k = stack.pop();
            Long x = stack.pop();
            while (k-1>0){
                temp.push(stack.pop());
                k--;
            }
            temp.push(x);
            
            while(!temp.isEmpty()){
                stack.push(temp.pop());
            }
            return stack;
        }
        
        return null;
    }

    /** Method that processes the operations of the operands.
     *
     *@param y a value representing a popped stack value
     *@param x a value representing a popped stack value
     *@param op representing the operand
     *
     *@return a Long value that is the sum of the operation
     */
    public static Long operation(Long y, Long x, String op){
        if (op.equals ("+")||op.equals("+!")){
            return x+y;
        }else if (op.equals ("/")||op.equals("/!")){
            try{
                return x/y;
            }catch (ArithmeticException e){
                System.out.println("Error: division by 0");
                return null;
            }
        }else if (op.equals ("*")||op.equals("*!")){
            return x*y;
        } else if (op.equals ("-")|| op.equals("-!")){
            return x-y;
        }else if (op.equals("%")|| op.equals("%!")){
            return x%y;
        }
        return null;
    }
        
}

  

    

