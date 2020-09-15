package com.example.calculaor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //加载计算器控件
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

    @Override
    //加载菜单
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);
        return true;
    }

    //菜单事件
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        switch(id){
            case R.id.main:
                Toast.makeText(this,item.getTitle(),Toast.LENGTH_SHORT).show();
                break;
            case R.id.base:
                Intent intent = new Intent(MainActivity.this, MainActivity_Conversion.class);
                startActivity(intent);
                Toast.makeText(this,item.getTitle(),Toast.LENGTH_SHORT).show();
                break;
            case R.id.unit:
                Intent intent2 = new Intent(MainActivity.this, MainActivity_unitConversion.class);
                startActivity(intent2);
                Toast.makeText(this,item.getTitle(),Toast.LENGTH_SHORT).show();
                break;
            case R.id.help:
                Intent intent3 = new Intent(MainActivity.this, MainActivity_help.class);
                startActivity(intent3);
                Toast.makeText(this,item.getTitle(),Toast.LENGTH_SHORT).show();
                break;
            case R.id.exit:
                System.exit(0);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //计算器功能
    private void selectButtonEvent(Button bt,TextView txt,TextView result){
        switch (bt.getText().toString()){
            case "C":
                txt.setText(null);
                result.setText(null);
                break;
            case "X":
                if(txt.getText().toString().length()!=0)
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
            case "1/x":
                try {
                    char lastStr = txt.getText().toString().charAt(txt.getText().toString().length() - 1);
                    float num = Float.parseFloat(txt.getText().toString());
                    if (txt.getText().toString().length() != 0 || Character.isDigit(lastStr) || lastStr==')' ){
                        txt.setText(txt.getText().toString() + "^(-1)");
                        result.setText(""+Math.pow(num,-1));
                    }
                }catch (Exception e){
                    result.setText("输入错误");
                }
                break;
            case "x²":
                try {
                    char lastStr = txt.getText().toString().charAt(txt.getText().toString().length() - 1);
                    if (txt.getText().toString().length() != 0 || Character.isDigit(lastStr) || lastStr==')' ){
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
                    if (txt.getText().toString().length() == 0 || Character.isDigit(lastStr) || lastStr==')' ) {
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
                        if (lastStr=='+' || lastStr=='-' || lastStr=='*' || lastStr=='/' || lastStr=='(') {
                            txt.setText(txt.getText().toString() + "sin");
                        }
                        else
                            result.setText("输入错误");
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
                        if (lastStr=='+' || lastStr=='-' || lastStr=='*' || lastStr=='/' || lastStr=='(') {
                            txt.setText(txt.getText().toString() + "cos");
                        } else
                            result.setText("输入错误");
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
                        if (lastStr=='+' || lastStr=='-' || lastStr=='*' || lastStr=='/' || lastStr=='(') {
                            txt.setText(txt.getText().toString() + "tan");
                        }else
                            result.setText("输入错误");
                    }catch (Exception e){
                        result.setText("输入错误");
                    }
                }
                break;
            case "ln":
                if(txt.getText().toString().length() == 0 )
                    txt.setText("ln");
                else{
                    try{
                        char lastStr = txt.getText().toString().charAt(txt.getText().toString().length() - 1);
                        if (lastStr=='+' || lastStr=='-' || lastStr=='*' || lastStr=='/' || lastStr=='(') {
                            txt.setText(txt.getText().toString() + "ln");
                        }
                        else
                            result.setText("输入错误");
                    }catch (Exception e){
                        result.setText("输入错误");
                    }
                }
                break;
            case "Log":
                if(txt.getText().toString().length() == 0 )
                    txt.setText("Log");
                else{
                    try{
                        char lastStr = txt.getText().toString().charAt(txt.getText().toString().length() - 1);
                        if (lastStr=='+' || lastStr=='-' || lastStr=='*' || lastStr=='/' || lastStr=='(') {
                            txt.setText(txt.getText().toString() + "Log");
                        }
                        else
                            result.setText("输入错误");
                    }catch (Exception e){
                        result.setText("输入错误");
                    }
                }
                break;
            case "x!":
                try {
                    char lastStr = txt.getText().toString().charAt(txt.getText().toString().length() - 1);
                    float num = Float.parseFloat(txt.getText().toString());
                    if (txt.getText().toString().length() != 0 || Character.isDigit(lastStr) || lastStr==')' ){
                        txt.setText(txt.getText().toString() + "!");
                        float sum = 1;
                        for(int i=1;i<=num;i++){
                            sum *=i ;
                        }
                        result.setText(""+sum);
                    }
                }catch (Exception e){
                    result.setText("输入错误");
                }
                break;
            case "%":
                try {
                    char lastStr = txt.getText().toString().charAt(txt.getText().toString().length() - 1);
                    float num = Float.parseFloat(txt.getText().toString());
                    if (txt.getText().toString().length() != 0 || Character.isDigit(lastStr) || lastStr==')' ){
                        txt.setText(txt.getText().toString() + "%");
                        result.setText(""+num/100);
                    }
                }catch (Exception e){
                    result.setText("输入错误");
                }
                break;
            case "√":
                try {
                    char lastStr = txt.getText().toString().charAt(txt.getText().toString().length() - 1);
                    float num = Float.parseFloat(txt.getText().toString());
                    if (txt.getText().toString().length() != 0 || Character.isDigit(lastStr) || lastStr==')' ){
                        txt.setText(txt.getText().toString() + "√");
                        result.setText(""+Math.sqrt(num));
                    }
                }catch (Exception e){
                    result.setText("输入错误");
                }
                break;
            case "x^y":
                try {
                    char lastStr = txt.getText().toString().charAt(txt.getText().toString().length() - 1);
                    if (txt.getText().toString().length() != 0 || Character.isDigit(lastStr) || lastStr==')'){
                        txt.setText(txt.getText().toString() + "^");
                        try{
                            result.setText(""+solve(txt.getText().toString()));
                        }catch (Exception e){
                            break;
                        }
                    }
                }catch (Exception e){
                    result.setText("输入错误");
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
            }else if(s[i] == 's'|| s[i] == 'c' || s[i] == 't' || s[i] == 'L' || s[i] == 'l' ){
                while(!stack.isEmpty() && compare(stack.peek(), s[i]) < 0) {
                    queue.add(stack.pop() + "");
                }
                stack.add(s[i]);
                if(s[i] == 'l')
                    i += 2;
                else
                    i += 3;
            }else {
                while (!stack.isEmpty() && compare(stack.peek(), s[i]) < 0) {
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
        Stack<Float> res = new Stack<Float>();
        while(!queue.isEmpty()){
            String t = queue.poll();
            if(t.equals("+")  || t.equals("-") || t.equals("*") || t.equals("/") || t.equals("^")){
                float a = res.pop();
                float b = res.pop();
                float result = cal(b, a, t);
                res.push(result);
            }else if(t.equals("s")  || t.equals("c") || t.equals("t") || t.equals("l") || t.equals("L")){
                float result = 0;
                float a = res.pop();
                float radians = (float)Math.toRadians(a);
                switch (t){
                    case "s":
                        result = (float)Math.sin(radians);
                        break;
                    case "c":
                        result = (float)Math.cos(radians);
                        break;
                    case "t":
                        result = (float)Math.tan(radians);
                        break;
                    case "l":
                        result = (float)Math.log(a);   //计算ln  ln等价于loge(x)
                        break;
                    case "L":
                        result = (float)Math.log10(a);   //计算log   logx(y) = loge(x)/loge(y)
                        break;
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
        if((c == '*' || c== '/') && (peek == '*' || peek == '/' || peek == 's' || peek == 'c' || peek == 't' || peek == 'l' || peek == 'L' || peek == '^')) return -1;
        if(peek == '^' && c == '^') return -1;   //幂函数优先级
        if((c == 's' || c == 'c' || c == 't')&& (peek == 'L' || peek == 'l' || peek == 's' || peek == 'c' || peek == 't')) return -1;  //三角函数优先级
        if((c == 'l' || c == 'L') && (peek == 'L' || peek == 'l' || peek == 's' || peek == 'c' || peek == 't')) return -1;   //l(ln)  L(log)
        return 1;
    }

}

