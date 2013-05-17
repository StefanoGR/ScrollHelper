/*******************************************************************************
 * Copyright 2013 Stefano G Rago
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.library.taomorpheus.scrollhelper;

 
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;

public class ListViewRotatingHelper implements AbsListView.OnScrollListener
{

	private final OnScrollListener externalListener;
	private final View indicator;
	private boolean isup; 
	private Float percentageOnRotation;
	private Float elementsInAnimation;

	/**
	 * @return create the helper using a view (an imageview with an arrow for example). Optional: customListener if you already have a listener on the listview
	 */
	public ListViewRotatingHelper(View indicator, OnScrollListener customListener) {
		this.indicator = indicator; 
		externalListener = customListener;
		this.setIsup(true); 
		this.setPercentageOnRotation(0.f);
		elementsInAnimation=30.f;//Default set of elements after the arrow goes up
	}
	
	public ListViewRotatingHelper(View indicator, OnScrollListener customListener, Float elementsInAnimation) {
		this.indicator = indicator; 
		externalListener = customListener;
		this.setIsup(true); 
		this.setPercentageOnRotation(0.f);
		this.elementsInAnimation=elementsInAnimation;
	}
	
	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) { 
 
		if(firstVisibleItem>elementsInAnimation){

			isup=false;  
			//rotation is finished, the arrow is now pointing up
		}else{
		
			Float x = 	 (((float)firstVisibleItem/elementsInAnimation));  
			Float percentageOnRotation = x * 180.f;
			if(Math.abs(percentageOnRotation-this.percentageOnRotation)>1.f){
			 
				
			RotateAnimation anim = new RotateAnimation(percentageOnRotation, percentageOnRotation, Animation.RELATIVE_TO_SELF,0.5f , Animation.RELATIVE_TO_SELF,0.5f );
			
			anim.setInterpolator(new LinearInterpolator()); 
			anim.setDuration(0);
			anim.setFillAfter(true);//Stay there!
			anim.setFillEnabled(true);//Stay there!
			indicator.startAnimation(anim);
			this.percentageOnRotation=percentageOnRotation;//The trick
		} 

		if(firstVisibleItem<visibleItemCount) isup=true; //Listview is somewhere in an up position. Doesn't matter if it's the first or the third element
		}
		
		
		if (externalListener != null) {
			externalListener.onScroll(view, firstVisibleItem, visibleItemCount, totalItemCount); //Call to the already existing listener
		}
		
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) { 
		if (externalListener != null) {
			externalListener.onScrollStateChanged(view, scrollState);
		}
		
	}

	/**
	 * @return the indicator (view, can be anything)
	 */
	public View getIndicator() {
		return indicator;
	}

	/**
	 * @return check if the listview is showing the first elements
	 */
	public boolean isUp() {
		return isup;
	}

	/**
	 * @param set isup
	 */
	private void setIsup(boolean isup) {
		this.isup = isup;
	}
 

	/**
	 * @return the percentage on a 180° Rotation
	 */
	public Float getPercentageOnRotation() {
		return percentageOnRotation;
	}

	/**
	 * @param  percentageOnRotation to set
	 */
	private void setPercentageOnRotation(Float percentageOnRotation) {
		this.percentageOnRotation = percentageOnRotation;
	}

}
