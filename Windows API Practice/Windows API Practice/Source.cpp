#ifndef UNICODE
#define UNICODE
#endif

#include <Windows.h>

//Function Prototype
LRESULT CALLBACK WindowProc(HWND hwnd, UINT uMsg, WPARAM wParam, LPARAM lParam);

int WINAPI wWinMain(HINSTANCE hInstance, HINSTANCE prevInstance, PWSTR pCmdLine, int nCmdShow){

	//Window class name
	const wchar_t CLASS_NAME[] = L"My Window";

	//Declare windows class structure
	WNDCLASS wc = {};

	//Fill out window class structure
	wc.lpfnWndProc = WindowProc;
	wc.hInstance = hInstance;
	wc.lpszClassName = CLASS_NAME;

	//Register Class
	RegisterClass(&wc);

	//Create Window
	HWND hwnd = CreateWindowEx(
		0,						//Optional windows styles
		CLASS_NAME,				//Window class name
		L"My First Window",		//Window Name
		WS_OVERLAPPEDWINDOW,	//Window Style

		//Size and position
		CW_USEDEFAULT, CW_USEDEFAULT, 
		CW_USEDEFAULT, CW_USEDEFAULT,

		NULL,					//Parent Window
		NULL,					//Menu
		hInstance,				//Instance Handle
		NULL					//Additional application data
		);

	//If window creation unsuccesfull, return 0
	if (hwnd == NULL){
		return 0;
	}
	//Else, show the created window;
	else{
		ShowWindow(hwnd, nCmdShow);
	}

	//Declare windows message structure
	MSG msg = {};

	//Set up message loop - runs while the message is not 0 (WM_QUIT)
	while (GetMessage(&msg, NULL, 0, 0)){
		TranslateMessage(&msg);
		DispatchMessage(&msg);
	}
}

LRESULT CALLBACK WindowProc(HWND hwnd, UINT uMsg, WPARAM wParam, LPARAM lParam){
	//Decode message and perform appropriate action
	switch (uMsg){
	case WM_SIZE: //Handles window resizing
		{
			int width = LOWORD(lParam); //Macro gets the low order word - in this case the window width
			int height HIWORD(lParam);  //Macro gets the high order word - in this case the window height
			break;
		}

	case WM_PAINT: //Handles window repainting
		{
			PAINTSTRUCT ps;
			HDC hdc = BeginPaint(hwnd, &ps);

			//Painting happens here between BeginPaint and EndPaint
			FillRect(hdc, &ps.rcPaint, (HBRUSH)(COLOR_WINDOW + 1));
			EndPaint(hwnd, &ps);

			break;
		}
	}


	//Return by then calling the defualt windows procedure
	return DefWindowProc(hwnd, uMsg, wParam, lParam);
}
