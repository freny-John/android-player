package com.cube.lush.player;

import android.app.Application;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.squareup.leakcanary.LeakCanary;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * @author Jamie Cruwys
 * @project lush-player-android-client
 */
public class MainApplication extends Application
{
	@Override public void onCreate()
	{
		super.onCreate();

		// LeakCanary must come first in onCreate
		if (LeakCanary.isInAnalyzerProcess(this)) {
			// This process is dedicated to LeakCanary for heap analysis.
			// You should not init your app in this process.
			return;
		}
		LeakCanary.install(this);

		CalligraphyConfig.initDefault(new CalligraphyConfig.Builder().setDefaultFontPath("fonts/HelveticaNeueLTPro-Roman.otf")
		                                                             .setFontAttrId(R.attr.fontPath)
		                                                             .addCustomViewWithSetTypeface(TextView.class)
		                                                             .build());

		LushContent.initialise();
		setupImageLoader();
	}

	private void setupImageLoader()
	{
		DisplayImageOptions imageOptions = new DisplayImageOptions.Builder()
			.resetViewBeforeLoading(true)
			.cacheInMemory(true)
			.cacheOnDisk(true)
			.imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2)
			.build();

		ImageLoaderConfiguration imageLoaderConfiguration = new ImageLoaderConfiguration.Builder(this)
			.writeDebugLogs()
			.defaultDisplayImageOptions(imageOptions)
			.build();

		ImageLoader.getInstance().init(imageLoaderConfiguration);
	}
}
