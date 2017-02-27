/*
Program : String Conversion
author : Ayush Joshi

Description : 1.) Write a function that takes a sentence as a string and returns a string with all the contractions instead
            (ie. "is not" becomes "isn't"). Convert at least [should, could, would, do, is, are, does, have] not => []n't
            and [should, could, would] have => []'ve. Many may occur in a sentence. Words will be delimited by single spaces
            and will not contain punctuation.

            2.)Multiple contractions can be chained in some cases: there's a famous Southern "y'all'd've"
             (you all would have) which y'all'd'ven't (you all would have not) heard of if y'all'evn't (you all have not
             (I may be making this one up)) been to the South. Additionally, Scottish slang allows for "am not" to be written as "amn't,"
             which ain't different from "ain't." Design an app to let users input sentences and get shortened (or even expanded) ones back.
             How would you allow them to conveniently configure your tool to handle all sorts of contractions
             (even their own, or their own odd chains)?
*/
package StringConversion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

@SuppressWarnings("serial")
public class StringConversion extends JFrame implements ActionListener, MouseListener
{
    JFrame f;
    JPanel p;

    JLabel l1,l2,l3,l4,l5;
    JTextField tf1, tf2;
    JButton b0,b1,b2,b3,b4,b5;

    String input;
    String output[];

    boolean converted = false;



    //String Type Array and Lists
    public String conversions[] = {"should", "could", "would", "do", "is", "are", "does", "have"};
    public String not_or_have[] = {"not", "have"};
    public List<String> printnot; //{"shouldn't", "couldn't", "wouldn't", "don't", "isn't", "aren't", "doesn't", "haven't"};
    public List<String> printhave; //{"should've", "could've", "would've"};
    public List<String> custom;//{"4you all would have", "5you all would have not", "4you all have not", "2am not"};
    public List<String> customprint; //{"y'all'd've", "y'all'd'ven't", "y'all'evn't","amn't"};
    public List<String> outputarray;
    public String a[] = {"shouldn't", "couldn't", "wouldn't", "don't", "isn't", "aren't", "doesn't", "haven't"};
    public String b[] = {"should've", "could've", "would've"};
    public String c[] = {"4you all would have", "5you all would have not", "4you all have not", "2am not"};
    public String d[] = {"y'all'd've", "y'all'd'ven't", "y'all'evn't","amn't"};

    JTextField field1 = new JTextField();
    JTextField field2 = new JTextField();

    Object[] message = {
            "Input Expanded:", field1,
            "Input Contracted:", field2,
    };

    public void display()
    {
        f=new JFrame("String Conversion");
        f.setExtendedState(Frame.MAXIMIZED_BOTH);
        f.setSize(500, 300);
        f.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width  - getWidth()) / 2-250,
                (Toolkit.getDefaultToolkit().getScreenSize().height - getHeight()) / 2-250);

        f.setResizable(false);

        f.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);


        p=new JPanel();
        p.setLayout(null);
        p.setBackground(Color.orange);
        f.add(p);

        l1=new JLabel("String Conversion");
        Font f3=new Font("Lucida Console",Font.BOLD,25);
        l1.setFont(f3);
        l1.setBounds(10,10,300,40);
        p.add(l1);

        l2=new JLabel("ShrinkD = Shrink Definition Defined, ExpandD = Expand Definition Defined");
        l2.setBounds(10,220,500,40);
        p.add(l2);

        l3=new JLabel("ShrinkU = Shrink User defined , ExpandU = Expand User Defined");
        l3.setBounds(10,235,500,40);
        p.add(l3);

        l4=new JLabel("Input String");
        l4.setBounds(20,50,300,40);
        p.add(l4);

        tf1=new JTextField(100);
        tf1.setBounds(100,50,390,40);
        p.add(tf1);

        l5=new JLabel("Output String");
        l5.setBounds(20,120,300,40);
        p.add(l5);

        tf2 = new JTextField(100);
        tf2.setBounds(100,120,390,40);
        tf2.setEnabled(false);
        p.add(tf2);


        b0=new JButton("Add");
        b0.setBounds(310,10,90,30);
        b0.addActionListener(this);
        p.add(b0);

        // b4.setBounds(400,200,92,30);
        //b0.addActionListener(this);
        //p.add(b0);


        b1=new JButton("ShrinkD");
        b1.setBounds(2,200,90,30);
        b1.addActionListener(this);
        p.add(b1);

        b2=new JButton("ExpandD");
        b2.setBounds(100,200,90,30);
        b2.addActionListener(this);
        p.add(b2);

        b3=new JButton("ShrinkU");
        b3.setBounds(200,200,90,30);
        b3.addActionListener(this);
        p.add(b3);

        b4=new JButton("ExpandU");
        b4.setBounds(300,200,90,30);
        b4.addActionListener(this);
        p.add(b4);

        b5=new JButton("Clear");
        b5.setBounds(400,200,92,30);
        b5.addActionListener(this);
        p.add(b5);


        f.addWindowListener (new WindowAdapter () {
                               public void windowClosing (WindowEvent we) {
                                   quitApp();
                               }
                           }
        );



        f.setVisible(true);
        setup();
    }
    public void setup()
    {
        printnot = new ArrayList<String>(Arrays.asList(a));
        printhave = new ArrayList<String>(Arrays.asList(b));
        custom = new ArrayList<String>(Arrays.asList(c));
        customprint = new ArrayList<String>(Arrays.asList(d));


    }
    @SuppressWarnings("deprecation")
    public void actionPerformed(ActionEvent ae)
    {
        if (ae.getSource() == b0)
        {
            int option = JOptionPane.showConfirmDialog(this, message, "Enter all your values", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION)
            {
                String value1 = field1.getText();
                String value2 = field2.getText();
                if (value1.equals("") == true || (value2.equals("") == true))
                {
                    JOptionPane.showMessageDialog(this, "Please Enter String!");
                    return;
                }
                else
                {
                    StringTokenizer num = new StringTokenizer(value1);
                    String no = Integer.toString(num.countTokens());
                    if(num.countTokens() > 9)
                    {
                        JOptionPane.showMessageDialog(this, "Cannot Add: Max limit 9 words!");
                        return;
                    }
                    else
                    {
                        StringBuilder strBuilder = new StringBuilder();
                        strBuilder.append(no);
                        strBuilder.append(value1);
                        custom.add(strBuilder.toString());
                        customprint.add(value2);
                    }
                }
            }
        }
        if (ae.getSource() == b1)
        {
            if ((tf1.getText().equals("") == true)) {
                JOptionPane.showMessageDialog(this, "Please Enter String");
                return;
            } else {
                input = tf1.getText();
                convert(input,'0');
                StringBuilder strBuilder = new StringBuilder();
                for (int i = 0; i < output.length; i++) {
                    if ((output[i] != null)) {
                        if (i == output.length - 1) {
                            strBuilder.append(output[i]);
                        } else {
                            strBuilder.append(output[i]);
                            strBuilder.append(" ");
                        }
                    }

                }
                String newoutput = strBuilder.toString();
                tf2.setEnabled(true);
                tf2.setEditable(false);
                tf2.setText(newoutput);


            }
        }

        if (ae.getSource() == b2)
        {
               /* for (int j = 0; j < custom.size(); j++) {

                    System.out.println(custom.get(j));
                }

                System.out.print("------------------");
                for (int j = 0; j < custom.size(); j++) {

                    System.out.println(customprint.get(j));
                }*/

            if ((tf1.getText().equals("") == true)) {
                JOptionPane.showMessageDialog(this, "Please Enter String");
                return;
            }
            else {
                {
                    input = tf1.getText();
                    convert(input, '1');
                    StringBuilder strBuilder = new StringBuilder();
                    for (int i = 0; i < outputarray.size(); i++)
                    {
                            if (i == outputarray.size() - 1)
                                strBuilder.append(outputarray.get(i));
                            else
                            {
                                strBuilder.append(outputarray.get(i));
                                strBuilder.append(" ");
                            }

                    }
                    String newoutput = strBuilder.toString();
/*                    input = newoutput;

                    convert(input,'1');
                    StringBuilder strBuild = new StringBuilder();
                    for (int i = 0; i < output.length; i++) {
                        if ((output[i] != null)) {
                            if (i == output.length - 1) {
                                strBuild.append(output[i]);
                            } else {
                                strBuild.append(output[i]);
                                strBuild.append(" ");
                            }
                        }
                    }*/
                    //newoutput = strBuild.toString();
                    tf2.setEnabled(true);
                    tf2.setEditable(false);
                    tf2.setText(newoutput);

                }

            }
            }

        if (ae.getSource() == b3)
        {

            if ((tf1.getText().equals("") == true)) {
                JOptionPane.showMessageDialog(this, "Please Enter String");
                return;
            }
            else
            {
                {
                    input = tf1.getText();
                    //StringTokenizer num = new StringTokenizer(input);
                    //int no = num.countTokens();
                    convert(input, 0);
                    StringBuilder strBuilder = new StringBuilder();
                    for (int i = 0; i < output.length; i++) {

                        if ((output[i] != null)) {
                            if (i == output.length - 1)
                                strBuilder.append(output[i]);
                            else {
                                strBuilder.append(output[i]);
                                strBuilder.append(" ");
                            }
                        }

                    }
                    String newoutput = strBuilder.toString();
                    input = newoutput;

                    convert(input, '0');
                    StringBuilder strBuild = new StringBuilder();
                    for (int i = 0; i < output.length; i++)
                    {
                        if ((output[i] != null))
                        {
                            if (i == output.length - 1)
                            {
                                strBuild.append(output[i]);
                            }
                            else
                            {
                                strBuild.append(output[i]);
                                strBuild.append(" ");
                            }
                        }
                    }
                    newoutput = strBuild.toString();
                    tf2.setEnabled(true);
                    tf2.setEditable(false);
                    tf2.setText(newoutput);

                }


            }


        }
        if (ae.getSource() == b4)
        {
            if ((tf1.getText().equals("") == true)) {
                JOptionPane.showMessageDialog(this, "Please Enter String");
                return;
            }
            else {
                {
                    input = tf1.getText();
                    convert(input, 1);
                    StringBuilder strBuild = new StringBuilder();
                    for (int i = 0; i < outputarray.size(); i++)
                    {
                        if (i == outputarray.size() - 1)
                            strBuild.append(outputarray.get(i));
                        else
                        {
                            strBuild.append(outputarray.get(i));
                            strBuild.append(" ");
                        }
                    }
                    input = strBuild.toString();
                    convert(input, '1');
                    StringBuilder strBuilder = new StringBuilder();
                    for (int i = 0; i < outputarray.size(); i++)
                    {
                        if (i == outputarray.size() - 1)
                            strBuilder.append(outputarray.get(i));
                        else
                        {
                            strBuilder.append(outputarray.get(i));
                            strBuilder.append(" ");
                        }

                    }
                    String newoutput = strBuilder.toString();
                    input = newoutput;
                    tf2.setEnabled(true);
                    tf2.setEditable(false);
                    tf2.setText(newoutput);

            }
        }
        }
        if(ae.getSource() == b5)
        {
            tf1.setText("");
            tf2.setEnabled(false);
            tf2.setText("");
        }
    }
    @SuppressWarnings("deprecation")
    @Override

    public void mouseClicked(MouseEvent a) {}

    public void mouseEntered(MouseEvent arg0) {}

    public void mouseExited(MouseEvent arg0) {}

    public void mousePressed(MouseEvent arg0) {}

    public void mouseReleased(MouseEvent arg0) {}

    void convert(String input, char z)
    {

        if(z == '0') {
            String[] result = input.split("\\s");
            output = new String[result.length];
            for (int i = 0; i < result.length; i++) {
                if ((i + 1) < result.length) {
                    int x = comp(result[i]);
                    int y = comp_not_or_have(result[i + 1]);
                    if (x >= 0 && y >= 0) {
                        if (y == 0) {
                            output[i] = printnot.get(x);
                            i++;
                        }
                        if (y == 1) {
                            output[i] = printhave.get(x);
                            i++;
                        }
                    } else {

                        output[i] = result[i];
                    }
                } else {
                    output[i] = result[i];
                }

            }
        }
        if(z== '1')
        {

            outputarray = new ArrayList<String>();
            String[] result = input.split("\\s");
            for (int i = 0; i < result.length; i++) {
                {
                    String found = compfind(result[i],'d');

                    if(found == null)
                    {
                        outputarray.add(result[i]);
                        continue;
                    }
                    if(found.charAt(0) == 'n')
                    {
                        outputarray.add(conversions[(Character.getNumericValue(found.charAt(1)))]);
                        outputarray.add("not");
                    }
                    if(found.charAt(0) == 'h')
                    {

                        outputarray.add(conversions[(Character.getNumericValue(found.charAt(1)))]);
                        outputarray.add("have");
                    }
                }

            }
        }
    }
    void convert(String input, int z)
    {
        if(z==0)
        {
            String[] result = input.split("\\s");
            int r = result.length;
            output = new String[r];
            for (int i = 0; i < r; i++)
            {
                if ((i + 1) < result.length)
                {
                    int x = comp(result, i, z);
                    if (x >= 0)
                    {
                        output[i] = customprint.get(x);
                        int t = Character.getNumericValue(custom.get(x).charAt(0));
                        i = i + t - 1;
                    }
                    else
                    {

                        output[i] = result[i];
                    }
                }
                else
                {
                    output[i] = result[i];
                }

            }
        }
        if(z==1)
        {
            outputarray = new ArrayList<String>();
            String[] result = input.split("\\s");
            for (int i = 0; i < result.length; i++) {
                {
                    String found = compfind(result[i],'u');

                    if (found == null) {
                        outputarray.add(result[i]);
                        continue;
                    }
                    if (found.charAt(0) == 'u') {
                        outputarray.add((custom.get(Character.getNumericValue(found.charAt(1)))).substring(1));
                    }

                }
            }
        }
    }
    String compfind(String word, char d)
    {
        if(d == 'd')
        {
                for (int j = 0; j < printnot.size(); j++) {
                    if (word.equalsIgnoreCase(printnot.get(j))) {
                        StringBuilder strBuild = new StringBuilder();
                        strBuild.append("n");
                        strBuild.append(Integer.toString(j));
                        return strBuild.toString();
                    }
                }
                for (int j = 0; j < printhave.size(); j++) {
                    if (word.equalsIgnoreCase(printhave.get(j))) {
                        StringBuilder strBuild = new StringBuilder();
                        strBuild.append("h");
                        strBuild.append(Integer.toString(j));
                        return strBuild.toString();
                    }
                }
        }
        if(d == 'u')
        {
            for (int j = 0; j < customprint.size(); j++) {
                if (word.equalsIgnoreCase(customprint.get(j))) {
                    StringBuilder strBuild = new StringBuilder();
                    strBuild.append("u");
                    strBuild.append(Integer.toString(j));
                    return strBuild.toString();
                }
            }
        }
        return null;
    }


    int comp(String word)
    {
        for(int j = 0; j <conversions.length;j++)
        {
            if(word.equalsIgnoreCase(conversions[j]))
            {
                return j;
            }
        }
        return -1;
    }
    int comp_not_or_have(String word)
    {
        for(int k = 0; k <not_or_have.length; k++)
        {
            if(word.equalsIgnoreCase(not_or_have[k]))
            {
                return k;
            }
        }
        return -1;
    }

    int comp(String word[], int y, int x)
    {
            for(int j = 0; j <custom.size();j++)
            {
                int q = y;
                int size = Character.getNumericValue(custom.get(j).charAt(0));
                if(y+size<=word.length)
                {
                    StringBuilder strBuilder = new StringBuilder();
                    strBuilder.append(size);
                    for(int z =0 ; z < size ; z++)
                    {
                        if(z==size-1)
                        {
                            strBuilder.append(word[q]);
                            q++;
                        }
                        else
                        {
                            strBuilder.append(word[q]);
                            strBuilder.append(" ");
                            q++;
                        }
                    }
                    String check = strBuilder.toString();
                    if (check.equalsIgnoreCase(custom.get(j)))
                    {
                        return j;
                    }
                }
            }
        return -1;
    }

    private void quitApp () {

        try {
            //Show a Confirmation Dialog.
            int reply = JOptionPane.showConfirmDialog (this,
                    "Do you really want to exit?",
                    "Exit", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);

            //Check the User Selection.
            if (reply == JOptionPane.YES_OPTION) {
                setVisible (false);	//Hide the Frame.
                JOptionPane.showMessageDialog(this, "Thank you!\nAuthor - Ayush Joshi!");
                dispose();            	//Free the System Resources.
                System.exit (0);        //Close the Application.
            }
            else if (reply == JOptionPane.NO_OPTION) {
                setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            }
        }

        catch (Exception e) {}

    }
    public static void main(String[] args)
    {
        StringConversion win=new StringConversion();
        win.display();
    }


}