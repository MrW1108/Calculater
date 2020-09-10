package com.example.calculaor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        onButtonClick();
    }


    private void onButtonClick(){
        final TextView txt = findViewById(R.id.txt);
        final TextView result = findViewById(R.id.result);
        GridLayout gridLayout = findViewById(R.id.gridLayout);
        for(int i=0;i<gridLayout.getChildCount()-2;i++){
            final Button bt = (Button)gridLayout.getChildAt(i+2);  //获取Button
            bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    selectButtonEvent(bt,txt,result);
                }
            });
        }
    }

    //按钮的功能
    private void selectButtonEvent(Button bt,TextView txt,TextView result){
        switch (bt.getText().toString()){
            case "C":
                txt.setText(null);
                result.setText(null);
                break;
            case "X":
                txt.setText(txt.getText().toString().substring(0,txt.getText().toString().length()-1));
                try {
                    result.setText("" + solve(txt.getText().toString()));
                }catch (Exception e){
                    break;
                }
                break;
            case "=":
                try{
                    result.setText(""+solve(txt.getText().toString()));
                    txt.setText(result.getText().toString());
                }catch (Exception e) {
                    break;
                }
                break;
            case ".":
                char lastStr = txt.getText().toString().charAt(txt.getText().toString().length()-1);
                if(Character.isDigit(lastStr))
                    txt.setText(txt.getText().toString() + bt.getText().toString());
                else
                    txt.setText(txt.getText().toString()+"0.");
                break;
            default:
                txt.setText(txt.getText().toString()+bt.getText().toString());
                try{
                    result.setText(""+solve(txt.getText().toString()));
                }catch (Exception e){

                }
                break;
        }
    }

    //将算术表达式转换成后缀表达式
    private static float solve(String str) {
        //将String类型转换成Char[],对字符数组进行遍历判断是数字还是操作符，遍历过程中如果是操作符那么直接入栈，
        //如果是 " )"那么需要弹出栈中的操作符号，并且把它加入到后缀表达式的队列中，一直到遇到符号栈中的 " ( " 为止，
        //如果不是上面两种那么可能是 +  -  *  / 这些符号或者是左括号那么这个时候需要判断符号栈中的栈顶元素与当前遍历到的字符的优先级的问题
        //同级弹出栈顶元素，再压入栈，直到优先级高与栈顶优先级,再入栈
        char[] s = str.toCharArray();

        Stack<Character> stack = new Stack<Character>();  //临时储存+-*/符号的栈
        Queue<String> queue = new LinkedList<String>();   //储存后缀表达式

        for(int i = 0; i < s.length;){
            if(s[i] >= '0' && s[i] <= '9'){
                float sum = 0;
                //特别要注意i < s.length这个条件
                while(i < s.length && s[i] >= '0' && s[i] <= '9'){
                    sum = sum * 10 + s[i] - '0';
                    i++;
                    /*System.out.println("i = " + i);*/
                }
                queue.add(Float.toString(sum));
            }else if(s[i] == ')'){
                while(!stack.isEmpty() && stack.peek() != '('){
                    queue.add(stack.pop() + "");
                }
                stack.pop();
                i++;
            }else{
                while(!stack.isEmpty() && compare(stack.peek(), s[i]) < 0){
                    queue.add(stack.pop() + "");
                }
                stack.add(s[i]);
                i++;
            }
        }
        while(!stack.isEmpty()){
            queue.add(stack.pop() + "");
        }
        //必须要使用.equals方法才正确使用 == 不正确
        Stack<Integer> res = new Stack<Integer>();
        while(!queue.isEmpty()){
            String t = queue.poll();
            if(t.equals("+")  || t.equals("-") || t.equals("*") || t.equals("/")){
                int a = res.pop();
                int b = res.pop();
                int result = cal(b, a, t);
                res.push(result);
            }else{
                res.add(Integer.parseInt(t));
            }
        }
        return res.pop();
    }

    private static int cal(int a, int b, String t) {
        //计算
        if(t.equals("+")){
            return a + b;
        }else if(t.equals("-")){
            return a - b;
        }else if(t.equals("*")){
            return a * b;
        }else{
            return a / b;
        }
    }

    private static int compare(char peek, char c) {
        if(peek == '(' || c == '(') return 1;
        if(c == '+' || c == '-') return -1;
        if(c == '*' && (peek == '*' || peek == '/'))return -1;
        if(c == '/' && (peek == '*' || peek == '/'))return -1;
        return 1;
    }

}