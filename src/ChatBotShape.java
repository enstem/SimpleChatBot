import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by дмитро on 28.03.2018.
 */
public class ChatBotShape extends JFrame implements ActionListener{
    final String NAME_OF_PROGRAM = "Chatbot: version 1.1";
    final int START_POSITION = 100;
    final int WINDOW_WIDTH = 350;
    final int WINDOW_HEIGHT = 450;

    JTextArea conversation;       //"dialog area"
    JCheckBox ai;                //enable/disable AI
    JTextField message;         //field for new messages
    SimpleBot sbot;             //chat-bot class
//    SimpleAttributeSet botStyle;//style bot text


    public static void main(String[] args) {
        new ChatBotShape();
    }
 public ChatBotShape(){
    setTitle(NAME_OF_PROGRAM);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setBounds(START_POSITION,START_POSITION,WINDOW_WIDTH,WINDOW_HEIGHT);

    //area for dialog
     conversation = new JTextArea();
     conversation.setLineWrap(true);
     JScrollPane scrollBar = new JScrollPane(conversation);

     //panel for checkbox,message and button
     JPanel bp = new JPanel();
     bp.setLayout(new BoxLayout(bp, BoxLayout.X_AXIS));
     ai = new JCheckBox("AI");
     ai.doClick();
     message = new JTextField();
     message.addActionListener(this);//enter for keyword
     JButton enter = new JButton("Enter");
     enter.addActionListener(this);

     //adding all elements to the window
     bp.add(ai);
     bp.add(message);
     bp.add(enter);
     add(BorderLayout.CENTER, scrollBar);
     add(BorderLayout.SOUTH, bp);
     setVisible(true);

     sbot = new SimpleBot();
}

    @Override
    public void actionPerformed(ActionEvent e) {
        if(message.getText().trim().length()>0){
        conversation.append(message.getText()+"\n");
        conversation.append(NAME_OF_PROGRAM.substring(0,9)+
        sbot.sayInReturn(message.getText(),ai.isSelected())+"\n");
        }
        message.setText("");
        message.requestFocusInWindow();
    }
}
