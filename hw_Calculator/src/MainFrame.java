import java.util.Stack;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class MainFrame extends javax.swing.JFrame {

    Stack<String> stackStr = new Stack<>();
    Stack<String> stackMR = new Stack<>();
    Stack<Integer> stackInt = new Stack<>();
    Stack<Double> stackDou = new Stack<>();
    ArrayList<String> infixModify = new ArrayList<>();
    
    public String StrOutput (ArrayList<String> infixModify) {
        String output = "";
        for(String i : infixModify) {
            output += i;
        }
        return output;
    }
    
    public int Priority(String item) {
        switch (item) {
            case "+":
            case "-":
                return 1;
            case "*":
            case "/":
                return 2;
        }
        return 0;
    }
   
    public boolean IsNumeric(String s) {
        try {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    public ArrayList<String> Modify(ArrayList<String> infixModify) {
        ArrayList<String> modify = new ArrayList<>();
        String item, num="";
        
        for(int i = 0; i < infixModify.size(); i++) {
            item = infixModify.get(i);
            if(IsNumeric(item)) {
                num += item;
            }
            if(item.equals(".")) {
                num += ".";
            }
            if(!IsNumeric(item) && !item.equals(".")) {
                modify.add(num);
                modify.add(item);
                num = "";
            }
            if(i == (infixModify.size() - 1))
                modify.add(num);
        }
        return modify;
    }
        
    public ArrayList<String> InfixToPostfix(ArrayList<String> infixModify) {
        stackStr.clear();
        ArrayList<String> postfixModify = new ArrayList<>();
        String item, op;
        
        infixModify = Modify(infixModify);
          
        for(int i = 0; i < infixModify.size(); i++) {            
            item = infixModify.get(i);
            if(IsNumeric(item)) {
                postfixModify.add(item);
            }
            else if(item.equals("+") || item.equals("-") || item.equals("*") || item.equals("/")) {
                while(!stackStr.isEmpty() && (Priority(item) <= Priority(stackStr.peek()))) {
                    postfixModify.add(stackStr.peek());
                }
                stackStr.push(item);
            }
        }
        while (!stackStr.isEmpty()) {
            op = stackStr.peek();
            stackStr.pop();
            postfixModify.add(op);
        }
        return postfixModify;
    }
    
    public double PostfixResult(ArrayList<String> postfixModify) {
        stackDou.clear();
        String item;
        double op1, op2, value, result;
        
        for(int i = 0; i < postfixModify.size(); i++) {
            item = postfixModify.get(i);
            if(!item.equals("+") && !item.equals("-") && !item.equals("*") && !item.equals("/")) {
                value = Double.parseDouble(item);
                stackDou.push(value);
            } else {
                op2 = stackDou.pop();
                op1 = stackDou.pop();
                switch(item) {
                    case "+":
                        stackDou.push(op1 + op2);
                        break;
                    case "-":
                        stackDou.push(op1 - op2);
                        break;
                    case "*":
                        stackDou.push(op1 * op2);
                        break;
                    case "/":
                        stackDou.push(op1 / op2);
                        break;
                }
            }
        }
        result = stackDou.pop();
        return result;
    }
    
    public MainFrame() {
        initComponents();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbl_MR = new javax.swing.JLabel();
        btn_Backspace = new javax.swing.JButton();
        btn_CE = new javax.swing.JButton();
        btn_C = new javax.swing.JButton();
        btn_MC = new javax.swing.JButton();
        btn_MR = new javax.swing.JButton();
        btn_MS = new javax.swing.JButton();
        btn_MPlus = new javax.swing.JButton();
        btn_0 = new javax.swing.JButton();
        btn_1 = new javax.swing.JButton();
        btn_2 = new javax.swing.JButton();
        btn_3 = new javax.swing.JButton();
        btn_4 = new javax.swing.JButton();
        btn_5 = new javax.swing.JButton();
        btn_6 = new javax.swing.JButton();
        btn_7 = new javax.swing.JButton();
        btn_8 = new javax.swing.JButton();
        btn_9 = new javax.swing.JButton();
        btn_numConv = new javax.swing.JButton();
        btn_float = new javax.swing.JButton();
        btn_add = new javax.swing.JButton();
        btn_sub = new javax.swing.JButton();
        btn_mul = new javax.swing.JButton();
        btn_div = new javax.swing.JButton();
        btn_sqrt = new javax.swing.JButton();
        btn_probability = new javax.swing.JButton();
        btn_fraction = new javax.swing.JButton();
        btn_result = new javax.swing.JButton();
        lbl_output = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lbl_MR.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl_MR.setToolTipText("");
        lbl_MR.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btn_Backspace.setText("Backspace");
        btn_Backspace.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_BackspaceActionPerformed(evt);
            }
        });

        btn_CE.setText("CE");
        btn_CE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_CEActionPerformed(evt);
            }
        });

        btn_C.setText("C");
        btn_C.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_CActionPerformed(evt);
            }
        });

        btn_MC.setText("MC");
        btn_MC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_MCActionPerformed(evt);
            }
        });

        btn_MR.setText("MR");
        btn_MR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_MRActionPerformed(evt);
            }
        });

        btn_MS.setText("MS");
        btn_MS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_MSActionPerformed(evt);
            }
        });

        btn_MPlus.setText("M+");
        btn_MPlus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_MPlusActionPerformed(evt);
            }
        });

        btn_0.setText("0");
        btn_0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_0ActionPerformed(evt);
            }
        });

        btn_1.setText("1");
        btn_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_1ActionPerformed(evt);
            }
        });

        btn_2.setText("2");
        btn_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_2ActionPerformed(evt);
            }
        });

        btn_3.setText("3");
        btn_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_3ActionPerformed(evt);
            }
        });

        btn_4.setText("4");
        btn_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_4ActionPerformed(evt);
            }
        });

        btn_5.setText("5");
        btn_5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_5ActionPerformed(evt);
            }
        });

        btn_6.setText("6");
        btn_6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_6ActionPerformed(evt);
            }
        });

        btn_7.setText("7");
        btn_7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_7ActionPerformed(evt);
            }
        });

        btn_8.setText("8");
        btn_8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_8ActionPerformed(evt);
            }
        });

        btn_9.setText("9");
        btn_9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_9ActionPerformed(evt);
            }
        });

        btn_numConv.setText("+/-");
        btn_numConv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_numConvActionPerformed(evt);
            }
        });

        btn_float.setText(".");
        btn_float.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_floatActionPerformed(evt);
            }
        });

        btn_add.setText("+");
        btn_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_addActionPerformed(evt);
            }
        });

        btn_sub.setText("-");
        btn_sub.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_subActionPerformed(evt);
            }
        });

        btn_mul.setText("*");
        btn_mul.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_mulActionPerformed(evt);
            }
        });

        btn_div.setText("/");
        btn_div.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_divActionPerformed(evt);
            }
        });

        btn_sqrt.setText("sqrt");
        btn_sqrt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_sqrtActionPerformed(evt);
            }
        });

        btn_probability.setText("%");
        btn_probability.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_probabilityActionPerformed(evt);
            }
        });

        btn_fraction.setText("1/x");
        btn_fraction.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_fractionActionPerformed(evt);
            }
        });

        btn_result.setText("=");
        btn_result.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_resultActionPerformed(evt);
            }
        });

        lbl_output.setBackground(new java.awt.Color(255, 255, 255));
        lbl_output.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl_output.setText("0.");
        lbl_output.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        lbl_output.setOpaque(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbl_output, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbl_MR, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_Backspace)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_CE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_C, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btn_MS, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                            .addComponent(btn_MC, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_MR, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                            .addComponent(btn_MPlus, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btn_7, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_4, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_0, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(btn_numConv, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_float, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(btn_2, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_3, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(btn_5, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_6, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(btn_8, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btn_9, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btn_div, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
                            .addComponent(btn_mul, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_sub, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_add, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btn_sqrt, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_probability, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_fraction, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                            .addComponent(btn_result, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_output, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_MR, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btn_Backspace)
                        .addComponent(btn_CE)
                        .addComponent(btn_C)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_sqrt)
                    .addComponent(btn_div)
                    .addComponent(btn_MC)
                    .addComponent(btn_9)
                    .addComponent(btn_7)
                    .addComponent(btn_8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_probability)
                    .addComponent(btn_mul)
                    .addComponent(btn_MR)
                    .addComponent(btn_6)
                    .addComponent(btn_4)
                    .addComponent(btn_5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_fraction)
                    .addComponent(btn_sub)
                    .addComponent(btn_MS)
                    .addComponent(btn_3)
                    .addComponent(btn_1)
                    .addComponent(btn_2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_result)
                    .addComponent(btn_add)
                    .addComponent(btn_MPlus)
                    .addComponent(btn_float)
                    .addComponent(btn_numConv)
                    .addComponent(btn_0))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_0ActionPerformed
        infixModify.add(btn_0.getText());
        lbl_output.setText(StrOutput(infixModify));
    }//GEN-LAST:event_btn_0ActionPerformed

    private void btn_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_1ActionPerformed
        infixModify.add(btn_1.getText());
        lbl_output.setText(StrOutput(infixModify));
    }//GEN-LAST:event_btn_1ActionPerformed

    private void btn_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_2ActionPerformed
        infixModify.add(btn_2.getText());
        lbl_output.setText(StrOutput(infixModify));
    }//GEN-LAST:event_btn_2ActionPerformed

    private void btn_3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_3ActionPerformed
        infixModify.add(btn_3.getText());
        lbl_output.setText(StrOutput(infixModify));
    }//GEN-LAST:event_btn_3ActionPerformed

    private void btn_4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_4ActionPerformed
        infixModify.add(btn_4.getText());
        lbl_output.setText(StrOutput(infixModify));
    }//GEN-LAST:event_btn_4ActionPerformed

    private void btn_5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_5ActionPerformed
        infixModify.add(btn_5.getText());
        lbl_output.setText(StrOutput(infixModify));
    }//GEN-LAST:event_btn_5ActionPerformed

    private void btn_6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_6ActionPerformed
        infixModify.add(btn_6.getText());
        lbl_output.setText(StrOutput(infixModify));
    }//GEN-LAST:event_btn_6ActionPerformed

    private void btn_7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_7ActionPerformed
        infixModify.add(btn_7.getText());
        lbl_output.setText(StrOutput(infixModify));
    }//GEN-LAST:event_btn_7ActionPerformed

    private void btn_8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_8ActionPerformed
        infixModify.add(btn_8.getText());
        lbl_output.setText(StrOutput(infixModify));
    }//GEN-LAST:event_btn_8ActionPerformed

    private void btn_9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_9ActionPerformed
        infixModify.add(btn_9.getText());
        lbl_output.setText(StrOutput(infixModify));
    }//GEN-LAST:event_btn_9ActionPerformed

    private void btn_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_addActionPerformed
        infixModify.add(btn_add.getText());
        lbl_output.setText(StrOutput(infixModify));
    }//GEN-LAST:event_btn_addActionPerformed

    private void btn_subActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_subActionPerformed
        infixModify.add(btn_sub.getText());
        lbl_output.setText(StrOutput(infixModify));
    }//GEN-LAST:event_btn_subActionPerformed

    private void btn_mulActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_mulActionPerformed
        infixModify.add(btn_mul.getText());
        lbl_output.setText(StrOutput(infixModify));
    }//GEN-LAST:event_btn_mulActionPerformed

    private void btn_divActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_divActionPerformed
        infixModify.add(btn_div.getText());
        lbl_output.setText(StrOutput(infixModify));
    }//GEN-LAST:event_btn_divActionPerformed

    private void btn_floatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_floatActionPerformed
        infixModify.add(btn_float.getText());
        lbl_output.setText(StrOutput(infixModify));
    }//GEN-LAST:event_btn_floatActionPerformed

    private void btn_numConvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_numConvActionPerformed
        stackStr.clear();
        int index = infixModify.size() - 1;
        String item, numStr = "";
        double num;
        
        for(int i = index; i >= 0; i--) {
            item = infixModify.get(i);
            if(item.equals("+") || item.equals("-") || item.equals("*") || item.equals("/")) {
                break;
            }
            stackStr.add(item);
            infixModify.remove(i);
        }
        while(!stackStr.isEmpty()) {
            numStr += stackStr.pop();
        }
        num = Double.parseDouble(numStr);
        num = num * -1;
        
        infixModify.add(Double.toString(num));
        lbl_output.setText(StrOutput(infixModify));
    }//GEN-LAST:event_btn_numConvActionPerformed

    private void btn_resultActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_resultActionPerformed
        ArrayList<String> postfixModify = new ArrayList<>();
  
        postfixModify = InfixToPostfix(infixModify);
        double result = PostfixResult(postfixModify);
        lbl_output.setText("" + result);
        
        infixModify.clear();
        infixModify.add(Double.toString(result));
    }//GEN-LAST:event_btn_resultActionPerformed

    private void btn_sqrtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_sqrtActionPerformed
        stackStr.clear();
        int index = infixModify.size() - 1;
        String item, numStr = "";
        double num;
        
        for(int i = index; i >= 0; i--) {
            item = infixModify.get(i);
            if(item.equals("+") || item.equals("-") || item.equals("*") || item.equals("/")) {
                break;
            }
            stackStr.add(item);
            infixModify.remove(i);
        }
        while(!stackStr.isEmpty()) {
            numStr += stackStr.pop();
        }
        num = Double.parseDouble(numStr);
        num = Math.sqrt(num);
        
        infixModify.add(Double.toString(num));
        lbl_output.setText(StrOutput(infixModify));
    }//GEN-LAST:event_btn_sqrtActionPerformed

    private void btn_probabilityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_probabilityActionPerformed
        stackStr.clear();
        int index = infixModify.size() - 1;
        String item, numStr = "";
        double num;
        
        for(int i = index; i >= 0; i--) {
            item = infixModify.get(i);
            if(item.equals("+") || item.equals("-") || item.equals("*") || item.equals("/")) {
                break;
            }
            stackStr.add(item);
            infixModify.remove(i);
        }
        while(!stackStr.isEmpty()) {
            numStr += stackStr.pop();
        }
        num = Double.parseDouble(numStr);
        num = num / 100;
        
        infixModify.add(Double.toString(num));
        lbl_output.setText(StrOutput(infixModify));
    }//GEN-LAST:event_btn_probabilityActionPerformed

    private void btn_fractionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_fractionActionPerformed
        stackStr.clear();
        int index = infixModify.size() - 1;
        String item, numStr = "";
        double num;
        
        for(int i = index; i >= 0; i--) {
            item = infixModify.get(i);
            if(item.equals("+") || item.equals("-") || item.equals("*") || item.equals("/")) {
                break;
            }
            if(IsNumeric(item)) {
                double check = Double.parseDouble(item);
                if(check == 0) {
                    JOptionPane.showMessageDialog(null, "0으로 나눌 수 없습니다");
                    return;
                }
            }
            stackStr.add(item);
            infixModify.remove(i);
        }
        while(!stackStr.isEmpty()) {
            numStr += stackStr.pop();
        }
        num = Double.parseDouble(numStr);
        num = 1 / num;
        
        infixModify.add(Double.toString(num));
        lbl_output.setText(StrOutput(infixModify));
    }//GEN-LAST:event_btn_fractionActionPerformed

    private void btn_BackspaceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_BackspaceActionPerformed
        if(!stackInt.isEmpty()) {
            int index = stackInt.pop();
            stackInt.push(index - 1);
        } else {
            stackInt.push(infixModify.size() - 1);
        }
        if(stackInt.isEmpty()) {
            JOptionPane.showMessageDialog(null, "읽어올 대상이 존재하지 않습니다.");
        }
        JOptionPane.showMessageDialog(null, "대상 : " + infixModify.get(stackInt.peek()));
        lbl_output.setText(StrOutput(infixModify));
    }//GEN-LAST:event_btn_BackspaceActionPerformed

    private void btn_CEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_CEActionPerformed
        int index = stackInt.peek();
        if(index < 0) {
            JOptionPane.showMessageDialog(null, "범위를 벗어났습니다.");
            return;
        }
        infixModify.remove(index);
        lbl_output.setText(StrOutput(infixModify));
        
        stackInt.clear();
    }//GEN-LAST:event_btn_CEActionPerformed

    private void btn_CActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_CActionPerformed
        infixModify.clear();
        lbl_output.setText(StrOutput(infixModify));
    }//GEN-LAST:event_btn_CActionPerformed

    private void btn_MCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_MCActionPerformed
        stackMR.clear();
        lbl_output.setText(StrOutput(infixModify));
        
        lbl_MR.setText("");
    }//GEN-LAST:event_btn_MCActionPerformed

    private void btn_MRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_MRActionPerformed
        if(stackMR.isEmpty()) {
            JOptionPane.showMessageDialog(null, "불러올 메모리가 없습니다.");
            return;
        }
        
        infixModify.add(stackMR.peek());
        lbl_output.setText(StrOutput(infixModify));
        
        lbl_MR.setText(stackMR.peek());
    }//GEN-LAST:event_btn_MRActionPerformed

    private void btn_MSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_MSActionPerformed
        
        stackMR.add(lbl_output.getText());
        infixModify.clear();
        lbl_output.setText(StrOutput(infixModify));
        
        lbl_MR.setText(stackMR.peek());
    }//GEN-LAST:event_btn_MSActionPerformed

    private void btn_MPlusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_MPlusActionPerformed
        if(stackMR.isEmpty()) {
            JOptionPane.showMessageDialog(null, "불러올 메모리가 없습니다.");
            return;
        }
        
        String stackNum = stackMR.peek();
        stackMR.pop();
        double stackNumInt = Double.parseDouble(stackNum);
        if(stackNumInt < 0) {
            stackNumInt = stackNumInt * -1;
        }
        stackNum = Double.toString(stackNumInt);
        stackMR.add(stackNum);
        
        lbl_MR.setText(stackMR.peek());
    }//GEN-LAST:event_btn_MPlusActionPerformed


    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_0;
    private javax.swing.JButton btn_1;
    private javax.swing.JButton btn_2;
    private javax.swing.JButton btn_3;
    private javax.swing.JButton btn_4;
    private javax.swing.JButton btn_5;
    private javax.swing.JButton btn_6;
    private javax.swing.JButton btn_7;
    private javax.swing.JButton btn_8;
    private javax.swing.JButton btn_9;
    private javax.swing.JButton btn_Backspace;
    private javax.swing.JButton btn_C;
    private javax.swing.JButton btn_CE;
    private javax.swing.JButton btn_MC;
    private javax.swing.JButton btn_MPlus;
    private javax.swing.JButton btn_MR;
    private javax.swing.JButton btn_MS;
    private javax.swing.JButton btn_add;
    private javax.swing.JButton btn_div;
    private javax.swing.JButton btn_float;
    private javax.swing.JButton btn_fraction;
    private javax.swing.JButton btn_mul;
    private javax.swing.JButton btn_numConv;
    private javax.swing.JButton btn_probability;
    private javax.swing.JButton btn_result;
    private javax.swing.JButton btn_sqrt;
    private javax.swing.JButton btn_sub;
    private javax.swing.JLabel lbl_MR;
    private javax.swing.JLabel lbl_output;
    // End of variables declaration//GEN-END:variables
}
