import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class RockPaperScissor{
    static JFrame frame= new JFrame("Title");

    static JFrame newFrame = new JFrame("Game");

    static CustomLabels customlabels[][];

    static Timer timer;

    static int val1=0,val2=0;

    static String winnerSymbol=null;
    
    static MyConstants constants=new MyConstants();
    public static void main(String[] args) {
        
        JTextField textField=new JTextField(10);
        
        
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        JPanel panel=new JPanel();
        JLabel label=new JLabel("Enter the number of players:");
        
        panel.add(label);
        panel.add(textField);
        
        frame.getContentPane().add(panel);
        // frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        
        textField.requestFocusInWindow();

        textField.addKeyListener(new KeyListener() {
            
            @Override
            public void keyTyped(KeyEvent e){}
            
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String value = textField.getText();
                    System.out.println(value);
                    ImgRenderer(Integer.parseInt(value));
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {}
        });
        // System.out.println(value);
    }
    private static void ImgRenderer(int value)
    {

        newFrame.setLayout(null);
        
        ImageIcon[] img=new ImageIcon[3];

        img[0]=new ImageIcon("D:\\Free time projects\\RPS_Simulation\\Assets\\Rock_3.png");
        img[1]=new ImageIcon("D:\\Free time projects\\RPS_Simulation\\Assets\\Paper_3.png");
        img[2]=new ImageIcon("D:\\Free time projects\\RPS_Simulation\\Assets\\Scissor_1.png");
        
        // Image[] small=new Image[3];
        // ImageIcon[] finalImg = new ImageIcon[3];

        value=value>constants.PIECE_COUNT?value=constants.PIECE_COUNT:value;

        
        JLabel[][] labels=new JLabel[value][3];
        
        newFrame.setSize(constants.SCREEN_WIDTH,constants.SCREEN_HEIGHT);
        newFrame.setVisible(true);
        newFrame.setLocationRelativeTo(null);
        newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Close the existing input window
        frame.dispose();

        customlabels=new CustomLabels[value][3];


        for(int i=0;i<value;i++)
        {
            for(int j=0;j<3;j++)
            {
                // small[i] = img[i].getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
                // finalImg[i]=new ImageIcon(small[i]);
                
                labels[i][j]=new JLabel(img[j]);


                val1=(int)Math.floor(Math.random()*constants.SCREEN_WIDTH);
                val2=(int)Math.floor(Math.random()*constants.SCREEN_HEIGHT);

                if(val1>650){
                    val1=val1-50;
                }
                if(val2>650){
                    val2=val2-50;
                }

                customlabels[i][j]=new CustomLabels(labels[i][j], val1, val2, constants.SYMBOLS[j]);
                
                customlabels[i][j].getLabel().setBounds(val1, val2, img[j].getIconWidth(),img[j].getIconHeight());
                newFrame.getContentPane().add(labels[i][j]);
                System.out.println(i+1+": "+val1+" "+val2);
            }
        }
        
        Randomness randomness=new Randomness();
        
        // StringBuilder sb=new StringBuilder();

        final int value1=value;

        final int topBound=constants.SCREEN_WIDTH-600;
        final int leftBound=constants.SCREEN_HEIGHT-600;

        final int rightBound=constants.SCREEN_WIDTH-50;
        final int bottomBound=constants.SCREEN_HEIGHT-50;

        timer = new Timer(constants.TIME_DELAY, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(int i=0;i<value1;i++)
                {
                    for(int j=0;j<3;j++)
                    {
                        int arr[]=randomness.getRandomness();
                        if((customlabels[i][j].getX_pos()+leftBound<=50 || customlabels[i][j].getY_pos()+topBound<=50) && (customlabels[i][j].getX_pos()+rightBound>=650 || customlabels[i][j].getY_pos()+bottomBound>=650))
                        {
                            arr=randomness.getRandomness();
                        }
                        System.out.println("NEW_POS: "+(i+1)+" "+arr[0]+" "+arr[1]+" "+customlabels[i][j].getSymbol());
                        customlabels[i][j].setX_pos(customlabels[i][j].getX_pos()+arr[0]);
                        customlabels[i][j].setY_pos(customlabels[i][j].getY_pos()+arr[1]);
                        customlabels[i][j].getLabel().setLocation(customlabels[i][j].getX_pos(),customlabels[i][j].getY_pos());
                    }
                    System.out.println();
                }
                for (int i = 0; i < value1; i++) {
                     // Avoid duplicate pairs
                    for (int j = 0; j < value1; j++) {
                        for (int k = 0; k < 3; k++) {
                            for(int l = 0; l < 3; l++)
                            {
                                if(i!=j && !customlabels[i][k].getSymbol().equals(customlabels[j][l].getSymbol()))
                                {
                                    Rectangle rect1 = customlabels[i][k].getLabel().getBounds();
                                    Rectangle rect2 = customlabels[j][l].getLabel().getBounds();
                                    if (rect1.intersects(rect2)) {
                                        if(customlabels[i][k].getSymbol().equals("Rock"))
                                        {
                                            //Enemy
                                            if(customlabels[j][l].getSymbol().equals("Paper"))
                                            {
                                                System.out.println(customlabels[i][k].getSymbol()+" "+(i+1)+" collided with "+customlabels[j][l].getSymbol()+" "+(j+1));
                                                customlabels[i][k].getLabel().setIcon((customlabels[j][l].getLabel().getIcon()));
                                                customlabels[i][k].setSymbol("Paper");
                                                newFrame.validate();
                                                newFrame.repaint();
                                            }

                                            //Capture
                                            else if(customlabels[j][l].getSymbol().equals("Scissor"))
                                            {
                                                System.out.println(customlabels[i][k].getSymbol()+" "+(i+1)+" collided with "+customlabels[j][l].getSymbol()+" "+(j+1));
                                                customlabels[j][l].getLabel().setIcon((customlabels[i][k].getLabel().getIcon()));
                                                customlabels[j][l].setSymbol("Rock");
                                                newFrame.validate();
                                                newFrame.repaint();
                                            }
                                        }
                                        else if(customlabels[i][k].getSymbol().equals("Paper"))
                                        {
                                            //Enemy
                                            if(customlabels[j][l].getSymbol().equals("Scissor"))
                                            {
                                                System.out.println(customlabels[i][k].getSymbol()+" "+(i+1)+" collided with "+customlabels[j][l].getSymbol()+" "+(j+1));
                                                customlabels[i][k].getLabel().setIcon((customlabels[j][l].getLabel().getIcon()));
                                                customlabels[i][k].setSymbol("Scissor");
                                                newFrame.validate();
                                                newFrame.repaint();
                                            }

                                            //Capture
                                            else if(customlabels[j][l].getSymbol().equals("Rock"))
                                            {
                                                System.out.println(customlabels[i][k].getSymbol()+" "+(i+1)+" collided with "+customlabels[j][l].getSymbol()+" "+(j+1));
                                                customlabels[j][l].getLabel().setIcon((customlabels[i][k].getLabel().getIcon()));
                                                customlabels[j][l].setSymbol("Paper");
                                                newFrame.validate();
                                                newFrame.repaint();
                                            }
                                        }
                                        else if(customlabels[i][k].getSymbol().equals("Scissor"))
                                        {
                                            //Enemy
                                            if(customlabels[j][l].getSymbol().equals("Rock"))
                                            {
                                                System.out.println(customlabels[i][k].getSymbol()+" "+(i+1)+" collided with "+customlabels[j][l].getSymbol()+" "+(j+1));
                                                customlabels[i][k].getLabel().setIcon((customlabels[j][l].getLabel().getIcon()));
                                                customlabels[i][k].setSymbol("Rock");
                                                newFrame.validate();
                                                newFrame.repaint();
                                            }

                                            //Capture
                                            else if(customlabels[j][l].getSymbol().equals("Paper"))
                                            {
                                                System.out.println(customlabels[i][k].getSymbol()+" "+(i+1)+" collided with "+customlabels[j][l].getSymbol()+" "+(j+1));
                                                customlabels[j][l].getLabel().setIcon((customlabels[i][k].getLabel().getIcon()));
                                                customlabels[j][l].setSymbol("Scissor");
                                                newFrame.validate();
                                                newFrame.repaint();
                                            }
                                        }
                                    }
                                    // Collision detected between player i and player j
                                    // sb.append("Collision between players " + i + " and " + j +"\n");
                                }
                            }
                        }
                    }
                }
                // if(winnerSymbol==null)
                //     winnerSymbol=winnerSymbol(value1,"Rock");
                // else if(winnerSymbol==null)
                //     winnerSymbol=winnerSymbol(value1,"Paper");
                // else if(winnerSymbol==null)
                //     winnerSymbol=winnerSymbol(value1,"Scissor");
                // System.out.println(sb);
                
            }
        });
        timer.start();
        // displayMessage(winnerSymbol);
        // timer.stop();
    }
    public static String winnerSymbol(int value)
    {
        for(int symb=0;symb<3;symb++)
        {
            for(int i=0;i<value;i++)
            {
                for(int k=0;k<3;k++)
                {
                    if(!customlabels[i][k].getSymbol().equals(constants.SYMBOLS[symb]))
                    {
                        return null;
                    }
                }
            }
        }
        return null;
    }

    private static void displayMessage(String message)
    {
        timer.stop();
        JOptionPane.showMessageDialog(newFrame, "winner is: "+ message, "Winner", JOptionPane.INFORMATION_MESSAGE);
    }
}
