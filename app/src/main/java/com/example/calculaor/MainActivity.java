package com.example.calculaor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                if(txt.getText().toString().length()==0 ) {
                    txt.setText(txt.getText().toString() + "0.");  //如果前面没有内容则直接0.
                }else {
                    char lastStr = txt.getText().toString().charAt(txt.getText().toString().length() - 1);
                    if (Character.isDigit(lastStr))
                        txt.setText(txt.getText().toString() + bt.getText().toString());
                    else
                        txt.setText(txt.getText().toString() + "0.");
                }
                break;
            case "x²":
                try {
                    char lastStr = txt.getText().toString().charAt(txt.getText().toString().length() - 1);
                    if (txt.getText().toString().length() == 0 || Character.isDigit(lastStr)) {
                        txt.setText(txt.getText().toString() + "^(2)");
                        result.setText(""+solve(txt.getText().toString()));
                    }
                }catch (Exception e){
                    result.setText("输入错误");
                }
                break;
            case "x³":
                try {
                    char lastStr = txt.getText().toString().charAt(txt.getText().toString().length() - 1);
                    if (txt.getText().toString().length() == 0 || Character.isDigit(lastStr)) {
                        txt.setText(txt.getText().toString() + "^(3)");
                        result.setText(""+solve(txt.getText().toString()));
                    }
                }catch (Exception e){
                    result.setText("输入错误");
                }
                break;
            case "sin":
                if(txt.getText().toString().length() == 0 )
                    txt.setText("sin");
                else{
                    try{
                        char lastStr = txt.getText().toString().charAt(txt.getText().toString().length() - 1);
                        if (!Character.isDigit(lastStr)) {
                            txt.setText(txt.getText().toString() + "sin");
                        }
                    }catch (Exception e){
                        result.setText("输入错误");
                    }
                }
                break;
            case "cos":
                if(txt.getText().toString().length() == 0 )
                    txt.setText("cos");
                else{
                    try{
                        char lastStr = txt.getText().toString().charAt(txt.getText().toString().length() - 1);
                        if (!Character.isDigit(lastStr)) {
                            txt.setText(txt.getText().toString() + "cos");
                        }
                    }catch (Exception e){
                        result.setText("输入错误");
                    }
                }
                break;
            case "tan":
                if(txt.getText().toString().length() == 0 )
                    txt.setText("tan");
                else{
                    try{
                        char lastStr = txt.getText().toString().charAt(txt.getText().toString().length() - 1);
                        if (!Character.isDigit(lastStr)) {
                            txt.setText(txt.getText().toString() + "tan");
                        }
                    }catch (Exception e){
                        result.setText("输入错误");
                    }
                }
                break;
            default:
                txt.setText(txt.getText().toString()+bt.getText().toString());
                try{
                    result.setText(""+solve(txt.getText().toString()));
                }catch (Exception e){
                    break;
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
                boolean flag = true;  //设置标志位，true为整数，false为小数
                int num = 0;  //小数的位数
                //特别要注意i < s.length这个条件
                while((i < s.length && s[i] >= '0' && s[i] <= '9') || (i < s.length && s[i] == '.')){
                    if(s[i] == '.') {
                        flag = false;
                    }
                    else{
                        if(flag) {
                            sum = sum * 10 + s[i] - '0';
                        }else {
                            num++;
                            sum = sum + (float) ((s[i] - '0') / Math.pow(10, num)); //pow()指数运算 s
                        }
                        /*System.out.println("i = " + i);*/
                    }
                    i++;
                }
                queue.add(Float.toString(sum));
            }else if(s[i] == ')'){
                while(!stack.isEmpty() && stack.peek() != '('){
                    queue.add(stack.pop() + "");
                }
                stack.pop();
                i++;
            }else{
                if(s[i] == 's'|| s[i] == 'c' || s[i] == 't') {
                    while(!stack.isEmpty() && compare(stack.peek(), s[i]) < 0) {
                        queue.add(stack.pop() + "");
                    }
                    stack.add(s[i]);
                    i += 3;
                }else{
                    while(!stack.isEmpty() && compare(stack.peek(), s[i]) < 0){
                        queue.add(stack.pop() + "");
                    }
                    stack.add(s[i]);
                    i++;
                }
            }
        }
        while(!stack.isEmpty()){
            queue.add(stack.pop() + "");
        }
        //必须要使用.equals方法才正确使用 == 不正确
        Stack<Float> res = new Stack<Float>();
        while(!queue.isEmpty()){
            String t = queue.poll();
            if(t.equals("+")  || t.equals("-") || t.equals("*") || t.equals("/") || t.equals("^")){
                float a = res.pop();
                float b = res.pop();
                float result = cal(b, a, t);
                res.push(result);
            }else if(t.equals("s")  || t.equals("c") || t.equals("t")){
                float result = 0;
                float a = res.pop();
                switch (t){
                    case "s":
                        result = (float)Math.sin(a);
                    case "c":
                        result = (float)Math.cos(a);
                    case "t":
                        result = (float)Math.tan(a);
                }
                res.push(result);
            }else{
                res.add(Float.parseFloat(t));
            }
        }
        return res.pop();
    }


    private static float cal(float a, float b, String t) {
        //计算
        BigDecimal bigDecimal_a = new BigDecimal(Float.toString(a));   //BigDecimal类解决浮点数精度问题
        BigDecimal bigDecimal_b = new BigDecimal(Float.toString(b));
        if(t.equals("+")){
            return Float.parseFloat(""+bigDecimal_a.add(bigDecimal_b));
        }else if(t.equals("-")){
            return Float.parseFloat(""+bigDecimal_a.subtract(bigDecimal_b));
        }else if(t.equals("*")){
            return Float.parseFloat(""+bigDecimal_a.multiply(bigDecimal_b));
        }else if(t.equals("^")){
            return (float)Math.pow(a,b);
        }else{
            return Float.parseFloat(""+bigDecimal_a.divide(bigDecimal_b,6,BigDecimal.ROUND_HALF_UP));
        }
    }

    private static int compare(char peek, char c) {
        if(peek == '(' || c == '(') return 1;
        if(c == '+' || c == '-') return -1;
        if(c == '*' && (peek == '*' || peek == '/'))return -1;
        if(c == '/' && (peek == '*' || peek == '/'))return -1;
        if(peek == '^' && c == '^') return -1;
        if(c == 's' && (peek == 's' || peek == 'c' || peek == 't'))return -1;
        if(c == 'c' && (peek == 's' || peek == 'c' || peek == 't'))return -1;
        if(c == 't' && (peek == 's' || peek == 'c' || peek == 't'))return -1;
        return 1;
    }

}

