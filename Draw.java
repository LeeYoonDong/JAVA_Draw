import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.io.Serializable;
import javax.swing.*;
import java.util.*;

class Shape implements Serializable{   //무엇을 그릴지 결정

   //기본적인 정보 저장
   private int x1,y1,x2,y2;   
   private int type;
   private Color color;
   private boolean filled;
   private Font font;
   private String str;

   public Shape(int x1,int y1,int x2,int y2,int type,Color color, boolean filled,Font font,String str ) {
      
   //기본적인 정보 설정
      this.x1=x1;
      this.y1=y1;
      this.x2=x2;
      this.y2=y2;
      this.type=type;
      this.color=color;
      this.filled = filled;
      this.font=font;
      this.str=str;
   }

   public int getX1() {return x1;}
   public void setX1(int x)   {this.x1=x;}
   public int getY1()   {return y1;}
   public void setY1(int y)   {this.y1=y;}
   public int getX2() {return x2;}
   public void setX2(int x) {this.x2=x;}
   public int getY2() {return y2;}
   public void setY2(int y)   {this.y2=y;}
   public int getType() {return type;}
   public void setType(int t) {this.type=t;}
   public Color getColor() {return color;}
   public void setColor(Color c)   {this.color = c;}
   public boolean isFilled() {return filled;}
   public void setFilled(boolean filled) {this.filled=filled;}
   public Font getFont() {return font;}
   public void setFont(Font f) {this.font=f;}
   public String getStr() {return str;}
   public void setStr(String str) {this.str=str;}
   //기본적인 값들을 설정, 얻을 수 있는 Shape클래스
}

class AddLable implements  ActionListener{

   Frame frame;
   Font font;
   
   Dialog strD;
   TextField userText;   //텍스트 필드 생성
   
   public String txt;   //입력한 String을 담는다
   
   public AddLable(Frame f ) {
      this.frame = f;
   }
   
   
   public void Dialog(int x , int y) {
      //텍스트 입력을 받아줌
      
      strD = new Dialog(frame, "텍스트 입력", true);
      strD.setSize(300,150);
      strD.setLocation(x,y);
      strD.setLayout( new FlowLayout() );
       Label msg = new Label("String");
       userText = new TextField(10);

       Button ok = new Button("OK");
       ok.setSize(50, 50);
       ok.addActionListener(this);
       
       strD.addWindowListener(new WindowAdapter() {
         public void windowClosing(WindowEvent e) {
            txt = "txt";
            strD.setVisible(false);
         }
      });   
              
       strD.add(msg );
       strD.add( userText );    
       strD.add(ok );    
       strD.setVisible(true);
   }
   
   @Override
   public void actionPerformed(ActionEvent e) {
      // TODO Auto-generated method stub
      if(e.getActionCommand().contentEquals("OK")) {
         txt = userText.getText();
         System.out.println(txt);
         strD.setVisible(false);
      }
   }
   
   
   
}
//Font설정
class SelectFont implements  ActionListener, ItemListener{
   private Frame frame;
   private Dialog info;
   
   JComboBox  fontslist;   
   Vector comboBoxItems;   
   CheckboxGroup fontstyle ;
   JSlider  fontslider;   
   
   
   public Font font = new Font("Serif", Font.PLAIN, 20);   //기본 폰트 값
      
   public SelectFont(Frame f) {
      this.frame = f;   
   }
   
   public void setFont(Font font){
      this.font = font;
   }
   
   public void Dialog() {
      //Font를 설정 할 다이어로그
       info = new Dialog(frame, "Font Selection", true);
       info.setSize(400,250);
       info.setLocation(100,100);
       info.setLayout( new GridLayout(4 , 1));
       Label msg = new Label("Choose Font , Style and Size", Label.CENTER);
       
       GraphicsEnvironment ge = null;
        ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        comboBoxItems=new Vector<String>();        
       Font[] fonts = ge.getAllFonts();
       int font_index = 0;       
       
       for(int i=0; i<fonts.length;i++){
          comboBoxItems.add(fonts[i].getFontName());
          if( fonts[i].getFontName().equals(font.getFontName()) ) {
             font_index =i;
          }           
       }
       DefaultComboBoxModel<String> model = new DefaultComboBoxModel(comboBoxItems);
       
       fontslist = new JComboBox(model);
       fontslist.setSelectedIndex( font_index );
       fontslist.addItemListener(this);
     
       //폰트스타일 결정
       fontstyle = new CheckboxGroup();
       Checkbox cb1 = new Checkbox("Plain", font.getStyle() == 0?true:false ,fontstyle);
       Checkbox cb2 = new Checkbox("Bold", font.getStyle() == 1?true:false ,fontstyle);
       Checkbox cb3 = new Checkbox("Italic", font.getStyle() == 2?true:false,fontstyle);
       Panel p = new Panel(); 
       p.add(cb1);
       p.add(cb2);
       p.add(cb3);
       cb1.addItemListener(this);
       cb2.addItemListener(this);
       cb3.addItemListener(this);
   
       
       //폰트 크기 설정
       Panel p1 = new Panel();
       p1.setLayout(new BorderLayout(0,0));
       p1.setBounds(0, 0, 90, 200);
       
       fontslider = new JSlider(00,0,100,100);   
       fontslider.setPaintLabels(true);
       fontslider.setPaintTicks(true);
       fontslider.setPaintTrack(true);
       fontslider.setMajorTickSpacing(5);
       fontslider.setMinorTickSpacing(1);
       fontslider.setValue(font.getSize());
       
       Button ok = new Button("OK");
       ok.setSize(50, 50);
       ok.addActionListener(this);
       
       p1.add(fontslider , BorderLayout.CENTER);
       p1.add(ok , BorderLayout.EAST);

       info.addWindowListener(new WindowAdapter() {
         public void windowClosing(WindowEvent e) {
            info.setVisible(false);
         }
      });   
              
       info.add(msg );
       info.add(fontslist );    
       info.add(p );
       info.add(p1 );    
       info.setVisible(true);
   }
   //액션리스너, 폰트의 스타일 설정을 위함
   @Override
   public void actionPerformed(ActionEvent event) {
      int int_font_size = fontslider.getValue();
      String string_font = (String) comboBoxItems.get(fontslist.getSelectedIndex());   //font스타일 설정
      
      if(fontstyle.getSelectedCheckbox().getLabel().equals("Plain")) {
         font = new Font( string_font , Font.PLAIN, int_font_size);
      }else if(fontstyle.getSelectedCheckbox().getLabel().equals("Bold")) {
         font = new Font( string_font , Font.BOLD, int_font_size);
      }else {
         font = new Font( string_font , Font.ITALIC, int_font_size);
      }
      
      if(event.getActionCommand().contentEquals("OK")) {         
         info.setVisible(false);
      }
      
      
   }

   @Override
   public void itemStateChanged(ItemEvent e) {
      int int_font_size = fontslider.getValue();
      String string_font = (String) comboBoxItems.get(fontslist.getSelectedIndex());  
      
      if(fontstyle.getSelectedCheckbox().getLabel().equals("Plain")) {
         font = new Font( string_font , Font.PLAIN, int_font_size);
      }else if(fontstyle.getSelectedCheckbox().getLabel().equals("Bold")) {
         font = new Font( string_font , Font.BOLD, int_font_size);
      }else {
         font = new Font( string_font , Font.ITALIC, int_font_size);
      }
      
   }

   

}

//그림을 그려준다
public class DrawPicture extends Frame implements Serializable,MouseListener,MouseMotionListener,ItemListener, ActionListener{


   SelectFont sf = new SelectFont(this);
   AddLable al = new AddLable(this);
   
   String txt;
   
   // 메뉴바를 생성
   private MenuBar mb = new MenuBar();
   
   //초기 컬러 설정값은 검정색
   Color c = new Color( 0 , 0 , 0 );

   // File메뉴 생성
   private Menu file = new Menu("File");
   private MenuItem fnew = new MenuItem("New");
   private MenuItem fopen = new MenuItem("Open");
   private MenuItem fsave = new MenuItem("Save");   
   private MenuItem fexit = new MenuItem("Exit");   
   
   //Shape메뉴 생성
   private Menu shape = new Menu("Shape");
   private CheckboxMenuItem s_line    = new CheckboxMenuItem("Line",true);   //기본값
   private CheckboxMenuItem s_oval    = new CheckboxMenuItem("Oval");
   private CheckboxMenuItem s_filloval = new CheckboxMenuItem("filledOval");
   private CheckboxMenuItem s_rect    = new CheckboxMenuItem("Rect");   
   private CheckboxMenuItem s_fillrect = new CheckboxMenuItem("filledRect");
   private CheckboxMenuItem s_string    = new CheckboxMenuItem("String");
   
   //Color메뉴 생성
   private Menu colors = new Menu("Color");
   private CheckboxMenuItem c_red = new CheckboxMenuItem("RED");
   private CheckboxMenuItem c_blue = new CheckboxMenuItem("BLUE");
   private CheckboxMenuItem c_yellow = new CheckboxMenuItem("YELLOW");
   private CheckboxMenuItem c_green = new CheckboxMenuItem("GREEN");
   private CheckboxMenuItem c_black = new CheckboxMenuItem("BLACK",true);   //기본값
   private CheckboxMenuItem c_white = new CheckboxMenuItem("WHITE");
   private CheckboxMenuItem c_custom = new CheckboxMenuItem("CUSTOM");
   
   //font메뉴 생성
   private Menu fonts = new Menu("Font");
   private MenuItem select = new MenuItem("Select");
   // x1,y1 : 도형 생성의 시작 
   // x2,y2 : 도형 생성의 마지막
   // dist : type을 쉽게 알아보기 위해서 int형으로 설정
   private int x1,y1,x2,y2;
   private int dist = 1;   //초기값을 line으로 설정
   
   Point startP=null;   //시작의 Point
   Point endP=null;   //끝의 Point
   Vector<Point> sp = new Vector<Point>(); // 시작의 Point를 저장할 Vector생성
   Vector<Point> ep = new Vector<Point>(); // 끝의 Point를 저장할 Vector 생성

   // Shape를 저장할 Vector를 설정, 그림삭제와 관리를 편하게 하기 위함 
   Vector<Shape> ShapeV = new Vector<Shape>();

   public DrawPicture() {
      super("그림판");
      //MenuBar을 생성
      this.setMenuBar(mb);
      //file메뉴생성
      mb.add(file);
      file.add(fnew);
      file.addSeparator();
      file.add(fopen);
      file.add(fsave);
      file.addSeparator();
      file.add(fexit);      
      
      //Shape메뉴 생성
      mb.add(shape);
      shape.add(s_line);
      shape.add(s_oval);
      shape.add(s_filloval);
      shape.add(s_rect);
      shape.add(s_fillrect);
      shape.add(s_string);
      
      //Color메뉴 생성
      mb.add(colors);
      colors.add(c_red);
      colors.add(c_blue);
      colors.add(c_yellow);
      colors.add(c_green);
      colors.add(c_black);
      colors.add(c_white);
      colors.add(c_custom);
      
      //Font메뉴 생성
      mb.add(fonts);
      fonts.add(select);
      
      this.addWindowListener(new WindowAdapter() {
         @Override
         public void windowClosing(WindowEvent e) {
            System.exit(0);
         }
      });      

      // 마우스 리스너 생성
      this.addMouseListener(this);
      this.addMouseMotionListener(this);
      
      // file에 대한 리스너를 생성
      fnew.addActionListener(this);
      fopen.addActionListener(this);
      fsave.addActionListener(this);
      fexit.addActionListener(this);
      
      //shape에 대한 리스너를 생성
      s_line.addItemListener(this);
      s_oval.addItemListener(this);
      s_filloval.addItemListener(this);
      s_rect.addItemListener(this);
      s_fillrect.addItemListener(this);
      s_string.addItemListener(this);
      

      //Color에 대한 리스너를 생성
      c_red.addItemListener(this);
      c_blue.addItemListener(this);
      c_yellow.addItemListener(this);
      c_green.addItemListener(this);
      c_black.addItemListener(this);
      c_white.addItemListener(this);
      c_custom.addItemListener(this);
      
      select.addActionListener(this);
      setSize(600, 600);
      setResizable(false);
      setVisible(true);
   }   




   @Override
   public void actionPerformed(ActionEvent e) {
      
      if( e.getActionCommand().equals("Select") ) {
         sf.Dialog();
      }
      
      if(e.getSource() == fnew){   
         //File의 New메뉴의 리스너 
         ShapeV.clear();
         x1= y1= x2 = y2 =  0;   // 초기화해 준다.
         dist = 1;
         this.repaint();
         
      }else if(e.getSource() == fopen){
         //File의 Open의 리스너
         JFileChooser fdlg = new JFileChooser();
         int returnVal = fdlg.showOpenDialog(null);
           ObjectInputStream objInputStream = null;
           FileInputStream inputStream = null;
           
           if(returnVal == 0) {
              File file = fdlg.getSelectedFile();
              
              try{

                 inputStream = new FileInputStream(file);
                 objInputStream = new ObjectInputStream (inputStream);
                 Vector<Shape> data = (Vector<Shape>) objInputStream.readObject();
                 objInputStream.close();
                 ShapeV = data;
                 this.repaint();
              } catch (FileNotFoundException ex) {
                 ex.printStackTrace();
              } catch (IOException ex) {
                 ex.printStackTrace();
              } catch (ClassNotFoundException ex) {
                 ex.printStackTrace();
              }finally{
                 if (objInputStream != null){
                    try{objInputStream.close();}catch (Exception ex){}
                 }else if (inputStream != null){
                    try{inputStream.close();}catch (Exception ex){}
                 }
              }
           }
           

      }else if(e.getSource() == fsave){
         //파일의 save의 리스너
         JFileChooser jfc = new JFileChooser();
         int returnVal1 = jfc.showSaveDialog(null);
         System.out.println(returnVal1);
           if(returnVal1 == 0) {
               File file = jfc.getSelectedFile();
                
               String str = "파일";
               ObjectOutputStream objOut = null;
               FileOutputStream fileOut = null;
               try {
                  fileOut = new FileOutputStream(file);
                  objOut = new ObjectOutputStream (fileOut);
                  objOut.writeObject(ShapeV);
                  objOut.close();                    
               }catch(Exception ex){
                  ex.printStackTrace();
               }
                
           }

      }else if(e.getSource() == fexit){
         System.exit(0);   
         //exit하면 프로그램 종료
      }
      

   }



   @Override
   public void itemStateChanged(ItemEvent e) {      
      //색 설정
      if( e.getSource() == c_red || e.getSource() == c_blue || e.getSource() == c_yellow || e.getSource() == c_green || e.getSource() == c_black || e.getSource() == c_white || e.getSource() == c_custom ){         
         c_red.setState(false);
         c_blue.setState(false);
         c_yellow.setState(false);
         c_green.setState(false);
         c_black.setState(false);
         c_white.setState(false);
         c_custom.setState(false);
         if( e.getSource() == c_red ) {
            c_red.setState(true);
            c = new Color( 255 , 0 , 0 );
         }else if( e.getSource() == c_blue ) {
            c_blue.setState(true);
            c = new Color( 0 , 0 , 255 );
         }else if( e.getSource() == c_yellow ) {
            c_yellow.setState(true);
            c = new Color( 255 , 255 , 0 );
         }else if( e.getSource() == c_green ) {
            c_green.setState(true);
            c = new Color( 0 , 255 , 0 );
         }else if( e.getSource() == c_black ) {
            c_black.setState(true);
            c = new Color( 0 , 0 , 0 );
         }else if( e.getSource() == c_white ) {
            c_white.setState(true);
            c = new Color( 255 , 255 , 255 );
         }else if( e.getSource() == c_custom ) {
            c_custom.setState(true);
            JColorChooser chooser = new JColorChooser(); //색 설정
            c = chooser.showDialog(null, "Color", c);
         }
      }
      
      
      if( e.getSource() == s_line || e.getSource() == s_oval || e.getSource() == s_filloval || e.getSource() == s_rect || e.getSource() == s_fillrect || e.getSource() == s_string   ) {
         // 도형을 그린다
         s_line.setState(false);
         s_oval.setState(false);
         s_filloval.setState(false);
         s_rect.setState(false);
         s_fillrect.setState(false);
         s_string.setState(false);
         if( e.getSource() == s_line ) {
            s_line.setState(true);
            dist = 1;
         }else if( e.getSource() == s_oval ) {
            s_oval.setState(true);
            dist = 2;
         }else if( e.getSource() == s_filloval ) {
            s_filloval.setState(true);
            dist = 3;
         }else if( e.getSource() == s_rect ) {
            s_rect.setState(true);
            dist = 4;
         }else if( e.getSource() == s_fillrect ) {
            s_fillrect.setState(true);
            dist = 5;
         }else if( e.getSource() == s_string ) {
            s_string.setState(true);
            dist = 6;
            al.Dialog(x1, y1);
            txt = al.txt;
         }         
      }
      
   }
   public void mouseMoved(MouseEvent e){}
   public void mouseClicked(MouseEvent e) {
      boolean flag = false;
      x1 = e.getX();
        y1 = e.getY();
        x2 = e.getX();
        y2 = e.getY();
      if(e.getButton()==MouseEvent.BUTTON3){   
         //클릭했을때 삭제할 것인지 물어본다
         int result = JOptionPane.showConfirmDialog(null,"삭제하겠습니까?", "도형삭제",JOptionPane.YES_NO_OPTION);
         if(result == JOptionPane.CLOSED_OPTION) {
            flag = false;
            
         }else if(result == JOptionPane.YES_OPTION) {
            flag = true;
            
         }else {
            flag = false;               
         }
            
         
          int xaa = e.getX();
          int yaa = e.getY();
           int vectorinx = 0;
           double vectornum = 999.0;
           for(int i=0 ; ShapeV.size() > i ; i++) {
              
              double qqq = calculate(xaa,yaa,ShapeV.get(i).getX1() , ShapeV.get(i).getY1());
              if( vectornum >= qqq ) {
                 vectorinx = i;
                 vectornum = qqq;
              }
           }
           if( ShapeV.size() != 0 && flag ) {
              Shape dd = ShapeV.get(vectorinx); 
              ShapeV.remove(vectorinx);
                Vector<Shape> tmp = new Vector<Shape>();
                for(int i =0; ShapeV.size() > i ; i++) {
                   tmp.add(ShapeV.get(i));
                }
                System.out.println(ShapeV.size());
                System.out.println(tmp.size());
                ShapeV.clear();
                x1 = e.getX();
                y1 = e.getY();
                x2 = e.getX();
                y2 = e.getY();
                      
                this.repaint(); 
                ShapeV = tmp;              
                this.repaint();
           }           
       }
      this.repaint();
   }
   public void mouseEntered(MouseEvent arg0) {   }
   public void mouseExited(MouseEvent arg0) {}
   
   
   
   @Override
   public void mouseDragged(MouseEvent e) {      
      if(e.getButton() !=MouseEvent.BUTTON3  ){ 
         x2 = e.getX();
         y2 = e.getY();
         this.repaint();
      }      
   }   

   //마우스 리스너
   public void mousePressed(MouseEvent e) {
      
      
       if( e.getButton()==MouseEvent.BUTTON3 ){        
             
       }else {
         x1 = e.getX();
         y1 = e.getY();
          if(dist == 6) {
                Shape di = new Shape(x1, y1, x2, y2, dist, c, false , sf.font , txt );               
                ShapeV.add(di);
               x1 = y1 = x2 = y2 =  0;   
               this.repaint();      
         }
          
       }
       
      
   }
   //내적하는 도형 구함
   private double calculate(int x1,int y1, int x2, int y2) {
      double angle =  Math.hypot(x1-x2, y1-y2);
      return angle;
   }
   
   
   @Override
   public void mouseReleased(MouseEvent e) {
      
      if( e.getButton()!=MouseEvent.BUTTON3 ) {
         x2 = e.getX();
         y2 = e.getY();
         txt = al.txt;      
         Shape di = new Shape(x1, y1, x2, y2, dist, c, false , sf.font , txt );   
         ShapeV.add(di);      
         this.repaint();
      }
      
      
      
   }
   
   @Override
   //dist로 그림의 타입 구분
   public void paint(Graphics g) {         
      g.setColor(c);
      
      if(dist == 1){g.drawLine(x1, y1, x2, y2);}
      else if(dist == 2){   g.drawRect(x1, y1, x2 - x1, y2 - y1);}
      else if(dist == 3){g.fillRect(x1, y1, x2-x1, y2-y1);}
      else if(dist == 4){g.drawOval(x1, y1, x2 - x1, y2 - y1);}
      else if(dist == 5) {g.fillOval(x1, y1, x2-x1, y2-y1);}
      else if(dist == 6){   
         g.setFont(sf.font);         
         g.drawString(txt,  x1,  y1);         
      }      
      loop(g);
   }
   //타입별로 도형을 그리기 때문에 if문을 사용
   
   private void loop( Graphics g ) {
      
      for(int i = 0; i < ShapeV.size(); i++){
   
         Shape imsi = ShapeV.get(i);

         g.setColor(imsi.getColor());

         if(imsi.getType() == 1 ){
            g.drawLine(imsi.getX1(), imsi.getY1(), imsi.getX2(), imsi.getY2());
         }else if(imsi.getType() == 2){                  
               g.drawRect(imsi.getX1(), imsi.getY1(), imsi.getX2() - imsi.getX1(), imsi.getY2() - imsi.getY1());                     
         }else if(imsi.getType() == 3){                  
            g.fillRect(imsi.getX1(), imsi.getY1(), imsi.getX2() - imsi.getX1(), imsi.getY2() - imsi.getY1());                  
         }else if(imsi.getType() == 4){
            g.drawOval(imsi.getX1(), imsi.getY1(), imsi.getX2() - imsi.getX1(), imsi.getY2() - imsi.getY1());
         }else if(imsi.getType() == 5){
            g.fillOval(imsi.getX1(), imsi.getY1(), imsi.getX2() - imsi.getX1(), imsi.getY2() - imsi.getY1());
         }else if(imsi.getType() == 6) {
            g.setFont(imsi.getFont());
            g.drawString( imsi.getStr() ,  imsi.getX1(),  imsi.getY1());            
         }
      }
      
   }


   public static void main(String[] args) {
      new DrawPicture();
   }
}