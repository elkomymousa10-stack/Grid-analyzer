import java.util.Scanner;
public class MiniProject{
    private static int[] rowSum,colSum; //used for more than 1 method so i created class variables
        public static void main(String[] args){
        Scanner scanner=new Scanner(System.in);//scanner object to take user input
        System.out.println("enter num of rows(min 6): ");
        int rows=scanner.nextInt();
        System.out.println("enter num of cols(min 6): ");
        int cols=scanner.nextInt();
        if(rows<6||cols<6){   //if statement to check if the input for rows and cols is valid and stops the program if it is not
            System.out.println("invalid input");
            return;
        }
        int[][] grid= new int[rows][cols];
        for(int r=0;r<rows;r++){
            for(int c=0;c<cols;c++){
                System.out.println("insert value for row "+r+" col "+c);
                grid[r][c]=scanner.nextInt();
            }
        }
        int Operation=-1;
        while(Operation!=0){//while loop to keep asking the user for operations until they choose to exit by entering 0
            System.out.println("Choose an operation:");
            System.out.println("1) Display the grid");
            System.out.println("2) Row and column aggregation");    
            System.out.println("3) Global maximum and minimum");
            System.out.println("4) Frequency counter");
            System.out.println("5) Pattern detection");
            System.out.println("6) Transformations");
            System.out.println("7) Subgrid processing");
            System.out.println("8) Boundary and diagonal processing");
            System.out.println("9) Validation algorithm");
            System.out.println("0) Exit");
            Operation=scanner.nextInt();
            switch (Operation) { //switch statement to select which operation to do based on user input
                case 1:
                    displayGrid(grid);
                    break;
                case 2:
                    rowAndColAggregation(grid);
                    break;
                case 3:
                    globalMaxAndMin(grid);
                    break;
                case 4:
                    System.out.println("insert target: ");
                    int target = scanner.nextInt();
                    System.out.println("insert threshold");
                    int threshold = scanner.nextInt();
                    frequencyCounter(grid, target, threshold);
                    break;
                case 5:
                    System.out.println("increasing row index: " + patternDetection(grid));
                    break;
                case 6:
                    System.out.println("Choose transformation");
                    System.out.println("1) Rotate a row right by 1");
                    System.out.println("2) Swap two rows");
                    System.out.println("3) Reverse a column");
                    int transformation = scanner.nextInt();
                    if (transformation == 1) {
                        System.out.println("Insert row index");
                        int index = scanner.nextInt();
                        if (index >= 0 && index < grid.length) {
                            rotateToRightByOne(grid, index);
                        } else {
                            System.out.println("invalid index");
                        }
                    }
                    if (transformation == 2) {
                        System.out.println("Insert first row");
                        int firstRow = scanner.nextInt();
                        System.out.println("insert second row");
                        int secondRow = scanner.nextInt();
                        if (firstRow >= 0 && firstRow < grid.length && secondRow >= 0 && secondRow < grid.length) {
                            swapTwoRows(grid, firstRow, secondRow);
                        } else {
                            System.out.println("invalid index");
                        }
                    }
                    if (transformation == 3) {
                        System.out.println("insert column index");
                        int colIndex = scanner.nextInt();
                        if (colIndex >= 0 && colIndex < grid[0].length) {
                            reverseCol(grid, colIndex);
                        } else {
                            System.out.println("invalid index");
                        }
                    }
                    break;
                case 7:
                    System.out.println("insert starting row");
                    int rowStart = scanner.nextInt();
                    System.out.println("insert ending row");
                    int endRow = scanner.nextInt();
                    System.out.println("insert starting column");
                    int startCol = scanner.nextInt();
                    System.out.println("insert ending column");
                    int endCol = scanner.nextInt();
                    boolean rowsValid = rowStart >= 0 && rowStart < grid.length && endRow >= 0 && endRow < grid.length && rowStart <= endRow; 
                    //boolean to check if the input for rows and columns of the subgrid is valid by checking if they are within the bounds of the grid and if the starting index is less than or equal to the ending index
                    boolean colsValid = startCol >= 0 && startCol < grid[0].length && endCol >= 0 && endCol < grid[0].length && startCol <= endCol;
                    if (rowsValid && colsValid) {
                        subGridProcessing(grid, rowStart, endRow, startCol, endCol);
                    } else {
                        System.out.println("invalid input");
                    }
                    break;
                case 8:
                    boundaryAndDiagonalProcessing(grid);
                    break;
                case 9:
                    System.out.println("Duplicates found: " + validationAlgorithm(grid));
                    break;
            }
        }
    }
    public static void displayGrid(int[][] grid){ //prints out the grid
        for(int r=0;r<grid.length;r++){
            for(int c=0;c<grid[0].length;c++){
                System.out.print(grid[r][c]+" ");
            }
            System.out.println();
        }
    }

    public static void rowAndColAggregation(int[][] grid){ //prints out the sum of each row and each col
        rowSum = new int[grid.length];
        for(int r=0;r<grid.length;r++){
            int sum=0;
            for(int c=0;c<grid[0].length;c++){
                sum+=grid[r][c];
            }
            rowSum[r]=sum;
            
        }
    
        colSum= new int[grid[0].length];
            for(int c=0;c<grid[0].length;c++){
            int sum=0;
            for(int r=0;r<grid.length;r++){
                sum+=grid[r][c];
            }
            colSum[c]=sum;
            }
            System.out.println("\n row sums: "); // enhanced loop for printing sums of rows and columns and /n for better formatting
            for(int s:rowSum){
                System.out.print(s+" ");
            }
            System.out.println("\n column sums: ");
            for(int s:colSum){
                System.out.print(s+" ");
            }
            System.out.println();   
    }
    public static void globalMaxAndMin(int[][] grid){//find max and min value and prints them and their indexes
        int minVal=grid[0][0];
        int minRow=0;
        int minCol=0;
        int maxVal=grid[0][0];
        int maxRow=0;
        int maxCol=0;
        for(int r=0;r<grid.length;r++){       
            for(int c=0;c<grid[0].length;c++){
                if(grid[r][c]<minVal){
                    minVal=grid[r][c];
                    minRow=r;
                    minCol=c;
                }
                if(grid[r][c]>maxVal){
                    maxVal=grid[r][c];
                    maxRow=r;
                    maxCol=c;
                }
            }
        }
        System.out.println("Max value is: "+ maxVal);
        System.out.println("Its index is "+maxRow+","+maxCol);
        System.out.println("minimum value is: "+minVal );
        System.out.println("its index is "+minRow+","+minCol);

    }
    public static void frequencyCounter(int[][] grid,int target,int threshold){// counts how many times target appears and how many values are greater than threshold and prints them
        int frequencyCount=0;
        int greaterValCount=0;
        for(int r=0;r<grid.length;r++){
            for(int c=0;c<grid[0].length;c++){
                if(grid[r][c]==target){
                    frequencyCount++;
                }
                if(grid[r][c]>threshold){
                    greaterValCount++;
                }
            }
        }
        System.out.println("occurences of "+target+": "+frequencyCount);
        System.out.println("Values greater than threshold: "+greaterValCount);
    }
    public static void rowAndColComparison(int[][] grid){ //compares row sums and column sums and prints the row with highest sum and column with lowest sum
        rowAndColAggregation(grid);
        int maxRowIndex=0;
        for(int i=0;i<rowSum.length;i++){
            if(rowSum[i]>rowSum[maxRowIndex]){
                maxRowIndex=i;
            }
        }
        int minColIndex=0;
        for(int i=0;i<colSum.length;i++){
            if(colSum[i]<colSum[minColIndex]){
                minColIndex=i;
            }
        }
        System.out.println("row with highest sum: "+maxRowIndex);
        System.out.println("column with lowest sum: "+minColIndex);
    }
    public static int patternDetection(int[][] grid){//detects if there is a row that is strictly increasing and returns its index or -1 if there is none
        for(int r=0;r<grid.length;r++){
            boolean currentRowIncreasing=true;
            for(int c=1;c<grid[0].length;c++){
                if(grid[r][c]<grid[r][c-1]){
                currentRowIncreasing=false;
                }
            }
            if(currentRowIncreasing){
                return r;
            }
        }
        return -1;
    }
    public static void rotateToRightByOne(int[][] grid,int rowIndex){//rotates a row to the right by 1
        int[] row=grid[rowIndex];
        int cols=row.length;
        int last=row[cols-1];
        int i=cols-1;
        while(i>0){
            row[i]=row[i-1];
            i--;
        }
        row[0]=last;
    }
    public static void swapTwoRows(int[][] grid, int firstRow,int secondRow){//swaps two rows by swapping their positions in a grid using a temporary 1d array
        int[] temp= grid[firstRow];
        grid[firstRow]=grid[secondRow];
        grid[secondRow]=temp;
    }
    public static void reverseCol(int[][] grid, int colIndex){//reverses a column by swapping values from top and bottom until they meet in the middle
        int top=0;
        int bottom=grid.length-1;
        while(top<bottom){
            int temp=grid[top][colIndex];
            grid[top][colIndex]=grid[bottom][colIndex];
            grid[bottom][colIndex]=temp;
            top++;
            bottom--;
        }
    }
    public static void subGridProcessing(int[][] grid, int rowStart,int rowEnd, int colStart, int colEnd){//processes a subgrid by calculating the sum of its values and finding the max value in it
        int sum=0;
        int maxVal=grid[rowStart][colStart];
        for(int r=rowStart;r<=rowEnd;r++){
            for(int c=colStart;c<=colEnd;c++){
                sum+=grid[r][c];
                if(grid[r][c]>maxVal){
                    maxVal=grid[r][c];
                }
            }
        }
        System.out.println("sum of subgrid: "+sum);
        System.out.println("max value in subgrid: "+maxVal);
    }
    public static void boundaryAndDiagonalProcessing(int[][] grid){//prints out the boundary elements and the main and secondary diagonals of the grid
        int rows=grid.length;
        int cols=grid[0].length;
        System.out.println("boundary elements: ");
        for(int r=0;r<rows;r++){
            for(int c=0;c<cols;c++){
                if(r==0||r==rows-1||c==0||c==cols-1){//if statement to check if the element is on the boundary and prints it if it is
                    System.out.print(grid[r][c]+" ");
                }
                else{System.out.print("  ");}
            }
            System.out.println();
        }
        int shortestSide=Math.min(rows,cols);//variable to store the length of the shorter side of the grid to prevent out of bounds error when printing diagonals
        System.out.println("main diagonal: ");
        for(int i=0;i<shortestSide;i++){
            System.out.print(grid[i][i]+" ");
        }
        System.out.println();
        System.out.println("secondary diagonal: ");
        for(int i=0;i<shortestSide;i++){
            System.out.print(grid[i][cols-i-1]+" ");//cols-i-1 to get the index of the secondary diagonal element in each row
        }
        System.out.println();
    }
    public static boolean validationAlgorithm(int[][] grid){//checks for duplicates in the grid by comparing each element to the elements to its right in the same row and returns true if a duplicate is found and false if there are no duplicates
        for(int r=0;r<grid.length;r++){
            for(int c=0;c<grid[0].length;c++){
                int target= grid[r][c];
                for(int i=c+1;i<grid[0].length;i++){
                    if(target==grid[r][i]){
                        return true;
                    }
                }
            }
        }
        return false;
    }
}