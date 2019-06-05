/**
 * Board object for the game forest
 * @author adwithyamagow
 *
 */
class Board{
	private char[][] board = new char[15][15];
	/**
	 * Draws the board
	 * 
	 */
	void DrawBoard() {
		for(int i =0;i<15;++i) {
			for(int j=0;j<15;++j) {
				if(this.GetValue(i, j) == '\u0000') {
					System.out.print('.');
				}
				else
					System.out.print(board[i][j]);
			}
			System.out.println();
		}
	}
	/**
	 * Set the value in the board
	 * @param row
	 * @param column
	 * @param character to set at that place
	 */
	void SetValue(int r, int c, char val) {
		board[r][c] = val;
	}
	/**
	 * Getter for the value at a row and column of the board
	 * @param row
	 * @param column
	 * @return character at the row and column 
	 */
	char GetValue(int r, int c) {

		return board[r][c];
	}
}