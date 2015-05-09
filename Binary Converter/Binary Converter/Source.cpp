/*
* Author: Liam Aitken
*/

#include <iostream>
#include <vector>

using namespace std;


//This function uses the method of short division by two and uses the remainder of either
//1 or 0 to obtain the bits, and then revereses the bits to get the correct pattern 
void solve(const int &input){

	//Dividend starts as equal to input and is continually halfed with each modulus until we reach 0
	int dividend = input;
	//The remainder of (dividend % 2) - either 1 or 0 - will make up the bit pattern
	int remainder;
	//Vector to hold the bit pattern
	vector<int> bits;

	//While the dividend is greater than zero, keep solving
	while (dividend != 0){
		//Calculate remainder
		remainder = dividend % 2;
		//Place remainder in bit pattern vector
		bits.push_back(remainder);
		//Half the dividend in preperation for the next iteration
		dividend /= 2;
	}

	//Print out bit pattern vector in reverse order, from last element to first, to get correct answer
	for (vector<int>::reverse_iterator itr = bits.rbegin(); itr != bits.rend();  itr++)
	{
		cout << *itr << "  ";
	}
}


int main(){

	//User defined decimal value
	int input;
	//Get user value
	cin >> input;
	//Call solving method
	solve(input);

	//Used so the program doesn't exit straight after the solve function has returned
	int hold;
	cin >> hold;

	return 0;
}