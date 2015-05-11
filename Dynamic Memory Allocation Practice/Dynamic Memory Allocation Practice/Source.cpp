/*
 * Author: Liam Aitken
 * This program requests a number from the user and prints from 1 up to the specified number. All the 
 * values are stored in a dynamcially allocated array, as the array size is calculated at run time
 */

#include <iostream>

using namespace std;

//Memory clean up function - takes pointer to the memory to be freed
void cleanUp(int* numbers){

	//Free memory
	delete[] numbers;
}

//Method populates array with numbers - takes a pointer to the array to be filled and the size parameter
//for that array
void fillArray(int* numbers, const int& size){

	//Fill the array - uses 'i + 1' to get numbers 1 to size rather than 0 to (size - 1)
	for (int i = 0; i < size; i++)
	{
		numbers[i] = i + 1;
	}
}

int main(){

	//User defined size variable
	int size;

	//Prompt user
	cout << "Specify how many numbers you would like to be printed: \n";

	//Get size from user
	cin >> size;

	//Dynamically allocate array according to the size
	int* p_array = new int[size];

	//Call fillArray function, passing pointer and size
	fillArray(p_array, size);

	//Print each element
	for (int i = 0; i < size; i++)
	{
		cout << p_array[i] << endl;
	}

	//Free memory - passing pointer to memory to be freed
	cleanUp(p_array);

	//To stop console application exiting straight away
	int hold;
	cin >> hold;
	return 0;
}