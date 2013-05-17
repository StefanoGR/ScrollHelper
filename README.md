ScrollHelper
============

Library project for Android. The scope is to nicely  animate a view according to the scroll status of a listview. 

For example It can be used to go on top of a list.



## 1. Description
It's a library project (it's possible to use directly the jar).
* **[scrollhelper-1.0.jar](https://dl.dropboxusercontent.com/u/854176/ScrollHelper/scrollhelper.jar)**
 


## 2. Usage of the ListViewRotatingHelper class
``` java
rotatingHelper = new ListViewRotatingHelper(moveList, myScrollListener); //myScrollListener can be null if you don't use any listener on the listview
listView.setOnScrollListener(rotatingHelper);

```

## 2. Example of the typical usage
``` java
  moveList.setOnClickListener(new OnClickListener() { //the view you are passing to the ListViewRotatingHelper
  	   	   	 

				public void onClick(View v) {
					
					if(rotatingHelper.isUp()) //If the first elements are shown, just go down with the list
						listView.smoothScrollBy(500,500);
					else
						listView.smoothScrollToPosition(0, 0); //Otherwise go up to the first element of the listview
				}
				
			});

```

It's free to use it, just add a reference to this github project.
