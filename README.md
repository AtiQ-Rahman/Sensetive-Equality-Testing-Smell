# Sensetive-Equality-Testing-Smell

Sensitive Equality smell means the existence of the toString method in the assertion statements. A code snippet is shown below where calculator.Add(num1,num2).toString() contains toString() method in assertEquals.

@Test
public void testAdd(){
	Integer num1=10;
	Integer num2=20;
	assertEquals("30", calculator.Add(num1,num2).toString());
}



For Run Source Code in Eclipse IDE----->
	
	1.Open the project 
	2.Import all AST Parser files(jdt) 
	Download Link "https://drive.google.com/drive/folders/1T0rAmIOWA28JEN1KrbMD3UTP_iG7bS6D?usp=sharing"
