package com.ssm.model;

public interface Sort {

	public enum Color{
		RED("红色",1),BLUE("蓝色",2),YELLOW("黄色",3),GREEN("绿色",3);
		
		private String name;
		private int num;
		
		private Color (String name,int num){
			this.name = name;  
			this.num = num; 
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getNum() {
			return num;
		}

		public void setNum(int num) {
			this.num = num;
		}
		
		
	}	
	
}
