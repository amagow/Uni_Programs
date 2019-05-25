#include <iostream>

//----------------------DO NOT CHANGE ANYTHING ABOVE THIS LINE------------------

void read_board(char arr[],int n)
{
  for(int i=0;i<n;i++)
  {
    std::cin>>arr[i];
  }
}
char evaluate_board(char arr[],int n)
{
  int c=0;
  for(int i=0;i<n;i++)
  {
    if(arr[i]!='x' && arr[i]!='o')
    {
      c++;
    }
  }
  if((arr[0]=='x'&& arr[1]=='x'&& arr[2]=='x') ||( arr[3]=='x'&& arr[4]=='x'&& arr[5]=='x') ||( arr[6]=='x'&& arr[7]=='x'&& arr[8]=='x') || (arr[0]=='x'&& arr[3]=='x' && arr[6]== 'x') || (arr[1]=='x'&& arr[4]=='x'&& arr[7]=='x')||(arr[2]=='x'&& arr[5]=='x'&& arr[8]=='x')||(arr[0]=='x'&& arr[4]=='x'&& arr[8]=='x') ||(arr[2]=='x'&& arr[4]=='x'&& arr[6]=='x') )
  {
    return 'x';
  }
  else if((arr[0]=='o'&& arr[1]=='o'&& arr[2]=='o') ||( arr[3]=='o'&& arr[4]=='o'&& arr[5]=='o') ||( arr[6]=='o'&& arr[7]=='o'&& arr[8]=='o') || (arr[0]=='o'&& arr[3]=='o' && arr[6]== 'o') || (arr[1]=='o'&& arr[4]=='o'&& arr[7]=='o')||(arr[2]=='o'&& arr[5]=='o'&& arr[8]=='o')||(arr[0]=='o'&& arr[4]=='o'&& arr[8]=='o') ||(arr[2]=='o'&& arr[4]=='o'&& arr[6]=='o') )
  {
    return 'o';
  }
  else
  {
    if(c==0)
    return 'd';
    else
    return 'u';
  }



}

//----------------------DO NOT CHANGE ANYTHING BELOW THIS LINE------------------

int main()
{
  const int SQUARES = 9;

  char board[SQUARES];

  read_board(board, SQUARES);
  char result = evaluate_board(board, SQUARES);

  switch (result)
  {
    case 'x':
    case 'o':
      std::cout << "Player " << result << " wins!!" << std::endl;
      break;
    case 'd':
      std::cout << "No winner. It's a draw!!" << std::endl;
      break;
    case 'u':
      std::cout << "No winner, but game is not over yet. Please continue." << std::endl;
      break;
    default:
      std::cout << "Sorry, I am broken." << std::endl;
  }

  return 0;
}
