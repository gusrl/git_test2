package mybatis;

public class MyBoardDTO {
		private int idx;
		private String id;
		private String name;
		private String contents;
		
		public MyBoardDTO() {
			// TODO Auto-generated constructor stub
		}

		public MyBoardDTO(int idx, String id, String name, String contents) {
			 
			this.idx = idx;
			this.id = id;
			this.name = name;
			this.contents = contents;
		}

		public int getIdx() {
			return idx;
		}

		public void setIdx(int idx) {
			this.idx = idx;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getContents() {
			return contents;
		}

		public void setContents(String contents) {
			this.contents = contents;
		}
		
}
