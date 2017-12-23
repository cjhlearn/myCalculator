package com.cjhlearn.mycalculator;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity implements OnClickListener{
    Button btn_0;
    Button btn_1;
    Button btn_2;
    Button btn_3;
    Button btn_4;
    Button btn_5;
    Button btn_6;
    Button btn_7;
    Button btn_8;
    Button btn_9;

    Button btn_divide;
    Button btn_multiply;
    Button btn_minus;
    Button btn_add;
    Button btn_equal;

    Button btn_point;
    Button btn_clear;
    Button btn_del;

    EditText edit_text_top;

    boolean flg=false;      //用于标明，对于计算过后的数值的操作

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout);
        //实例化数字按钮
        btn_0=(Button) findViewById(R.id.btn_0);
        btn_1=(Button) findViewById(R.id.btn_1);
        btn_2=(Button) findViewById(R.id.btn_2);
        btn_3=(Button) findViewById(R.id.btn_3);
        btn_4=(Button) findViewById(R.id.btn_4);
        btn_5=(Button) findViewById(R.id.btn_5);
        btn_6=(Button) findViewById(R.id.btn_6);
        btn_7=(Button) findViewById(R.id.btn_7);
        btn_8=(Button) findViewById(R.id.btn_8);
        btn_9=(Button) findViewById(R.id.btn_9);
        //实例化运算符号
        btn_divide=(Button) findViewById(R.id.btn_divide);
        btn_multiply=(Button) findViewById(R.id.btn_multiply);
        btn_add=(Button) findViewById(R.id.btn_add);
        btn_minus=(Button) findViewById(R.id.btn_minus);
        btn_equal=(Button) findViewById(R.id.btn_equal);
        //实例化其他符号
        btn_point=(Button) findViewById(R.id.btn_point);
        btn_del=(Button) findViewById(R.id.btn_del);
        btn_clear=(Button) findViewById(R.id.btn_clear);
        //实例化文本框
        edit_text_top=(EditText) findViewById(R.id.edit_text_top);

        //设置点击事件
        btn_0.setOnClickListener(this);
        btn_1.setOnClickListener(this);
        btn_2.setOnClickListener(this);
        btn_3.setOnClickListener(this);
        btn_4.setOnClickListener(this);
        btn_5.setOnClickListener(this);
        btn_6.setOnClickListener(this);
        btn_7.setOnClickListener(this);
        btn_8.setOnClickListener(this);
        btn_9.setOnClickListener(this);

        btn_divide.setOnClickListener(this);
        btn_multiply.setOnClickListener(this);
        btn_minus.setOnClickListener(this);
        btn_add.setOnClickListener(this);
        btn_equal.setOnClickListener(this);

        btn_point.setOnClickListener(this);
        btn_del.setOnClickListener(this);
        btn_clear.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        String str=edit_text_top.getText().toString();
        //设置点击事件
        switch (v.getId()){
            case R.id.btn_0:
            case R.id.btn_1:
            case R.id.btn_2:
            case R.id.btn_3:
            case R.id.btn_4:
            case R.id.btn_5:
            case R.id.btn_6:
            case R.id.btn_7:
            case R.id.btn_8:
            case R.id.btn_9:
            case R.id.btn_point:
                if(flg) {
                    str = "";
                }
                edit_text_top.setText(str+((Button)v).getText());
                flg=false;

                break;

            case R.id.btn_divide:
            case R.id.btn_multiply:
            case R.id.btn_minus:
            case R.id.btn_add:
                flg=false;
                edit_text_top.setText(str+((Button)v).getText());
                break;

            case R.id.btn_equal:
                flg=true;
                getResult();
                break;

            case R.id.btn_del:
                if(!flg){
                    if(str!=null&&str!=""){
                        edit_text_top.setText(str.substring(0,str.length()-1));
                    }
                }else{
                    edit_text_top.setText("");
                }
                break;
            case R.id.btn_clear:
                flg=false;
                edit_text_top.setText("");
                break;
        }
    }
    private void getResult(){
        float total=0;
        String number_total=edit_text_top.getText().toString();
        if(number_total==null&&number_total==""){
            return;
        }
        if(!number_total.contains("")){
            return;
        }

        String op=getOpIndex(number_total).substring(0,1);
        int opIndex=Integer.parseInt(getOpIndex(number_total).substring(1));
        String str1=number_total.substring(0,opIndex);
        String str2=number_total.substring(opIndex+1);
        Log.i("str1",str1);
        Log.i("str2",str2);
        Log.i("op",op);

        if(!str1.equals("")&&!str2.equals("")){
            float d1=Float.parseFloat(str1);
            float d2=Float.parseFloat(str2);
            if(op.equals("+")){
                total=d1+d2;
            }else if(op.equals("-")){
                total=d1-d2;
            }else if(op.equals("*")){
                total=d1*d2;
            }else if(op.equals("/")){
                if(d2==0){
                    total=0;
                }else {
                    total=d1/d2;
                }
            }
            edit_text_top.setText(total+"");
        }else if(str1.equals("")&&!str2.equals("")){
            float d2=Float.parseFloat(str2);
            if(op.equals("+")){
                total=0+d2;
            }else if(op.equals("-")){
                total=0-d2;
            }else if(op.equals("*")){
                total=d2;
            }else if(op.equals("/")){
                total=d2;
            }
            edit_text_top.setText(total+"");
        }else if(!str1.equals("")&&str2.equals("")){
            float d1=Float.parseFloat(str1);
            if(op.equals("+")){
                total=d1;
            }else if(op.equals("-")){
                total=d1;
            }else if(op.equals("*")){
                total=0;
            }else if(op.equals("/")){
                total=d1;
            }
            edit_text_top.setText(total+"");
        }else{
            total=0;
            edit_text_top.setText(total+"");
        }

    }

    private String getOpIndex(String s){
        String opStr;
        String tag="+";
        int index;
        index=s.indexOf("+");
        /*注意下面不能用if else*/
        if(index==-1){
            index=s.indexOf("-");
            tag="-";
        }
        if(index==-1){
            index=s.indexOf("*");
            tag="*";
        }
        if(index==-1){
            index=s.indexOf("/");
            tag="/";
        }
        opStr=tag+index;

        return opStr;
    }


}
