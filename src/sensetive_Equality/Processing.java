package sensetive_Equality;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.ContinueStatement;
import org.eclipse.jdt.core.dom.Javadoc;
import org.eclipse.jdt.core.dom.MarkerAnnotation;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.MethodInvocation;

import utility.Flag;





public class Processing
{
	
	static JFrame f;
	static JLabel l;
	static JTextArea jt;
	static JScrollPane scroll;
	static boolean b = false;
	static int count=0;
	

	
	
    public Processing(String rootFileName)
    {
    	File rootFile = new File(rootFileName);
        browseClasses(rootFile);
    }

public void browseClasses(File rootFile) {
		
		f = new JFrame("Sensetive Equality");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jt = new JTextArea();
		jt.setEditable(false);
		JPanel p = new JPanel();
		p.add(jt);

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		f.setSize(screenSize.width, screenSize.height);
		f.add(p);

		jt.setSize(screenSize.width, screenSize.height);
		scroll = new JScrollPane(jt);

		f.add(scroll);

		f.setVisible(true);

		StringBuilder sb = new StringBuilder();
		StringBuilder err = new StringBuilder();
		
		err.append("Serial\t\t,Line Number\t,\tFile Name\t,\t\tFilePath\t\t,"+"\n");
    	new ProjectExplorer((level, path, file) -> path.endsWith(".java"), (level, path, file) -> {
    		
    		String str = readUsingBufferedReaderCharArray(file);
    		
    	if(str.contains("import junit")||str.contains("org.junit")) {
    	
    		ASTParser parser = ASTParser.newParser(AST.JLS3);
    		parser.setSource(str.toCharArray());
    		parser.setKind(ASTParser.K_COMPILATION_UNIT);
    		
    		final CompilationUnit code = (CompilationUnit) parser.createAST(null);
    		
    		
    		 	
           
    		
    		code.accept(new ASTVisitor() {
    			
    			
    	
				@Override
    			public boolean visit(MethodInvocation node) {
    				
    					
					
    				
    					final String REGEX = "assert";
    					final String INPUT = node.getName().toString();

    				   
    				     Pattern p = Pattern.compile(REGEX);
    				     Matcher m = p.matcher(INPUT);  
    				
    				
                    final Flag flag = new Flag();
    				
                    if(m.find()){
    					
                        node.accept(new ASTVisitor() {
    						    						
    				
    						@Override
    						public boolean visit(MethodInvocation node1) {
    							
    							
    							if (node1.getName().toString().equals("toString")) {

    								int l = code.getLineNumber(node1.getStartPosition());
  								
    								if(!flag.isChecked()){
                    					

    									
    									count++;
    									sb.append(count+" . Found in line number: " + l + " ----->  in "+ file.getName()+"\n"+file.getAbsolutePath()+"\n");
    									
    									err.append(count+"," + l + ","+ file.getName()+","+file.getAbsolutePath()+"\n");
                                        
        								
        								
                                    	flag.check();
                                    }

    								
    								b = true;
    						
    								
    							}
    						
    								return super.visit(node1);
    						}
    					});
    				}

    				return super.visit(node);
    			}

				@Override
				public boolean visit(MarkerAnnotation node) {
				
					return super.visit(node);
				}
				
    		});
    		
    		if (b == false)
    			sb.append("Not Found in " + file.getName()+"\n");
    		 
		

    		jt.setText("Total Erorr --> "+ count + "\n"+ sb.toString()+"\n");
    		
    		
             try {
            	
            	FileWriter fw=new FileWriter("res\\BSSE0908.csv");   

  
            	fw.write(err.toString()+"\n");
            	fw.write("Total Erorr Found ------>"+count);

            	fw.close();  
			} catch (IOException e) {
			
				e.printStackTrace();
			}    
               
    		
    		}

    		
        }).explore(rootFile);
    }
	
	
	
    private static String readUsingBufferedReaderCharArray(File file) {
		BufferedReader reader = null;
		StringBuilder stringBuilder = new StringBuilder();
		char[] buffer = new char[10];
		try {
			reader = new BufferedReader(new FileReader(file));
			while (reader.read(buffer) != -1) {
				stringBuilder.append(new String(buffer));
				buffer = new char[10];
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null)
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}

		return stringBuilder.toString();
	}
}