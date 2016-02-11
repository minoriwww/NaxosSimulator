/*
 * Copyright (c) 2015 Michal Markiewicz
 * 
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 * 
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */ 

/**
 * 2012-06-24
 */
package de.tzi.traffic.lights;

import java.util.Random;

import de.tzi.config.GlobalConfiguration;
import de.tzi.config.SettingsKeys;
import de.tzi.traffic.Crossing;
import de.tzi.traffic.TrafficManager;
import de.tzi.traffic.properties.PropertyManager.CrossingProperty;

/**
 * @author Michal Markiewicz
 *
 */
public class RandomLightController extends LightController {

	final Random random;
	
	public RandomLightController(TrafficManager trafficManager) {
		super(trafficManager);
		random = trafficManager.getRandom();
	}
	
	final static int MIN = GlobalConfiguration.getInstance().getInt(SettingsKeys.RANDOM_MIN);
	final static int MAX = GlobalConfiguration.getInstance().getInt(SettingsKeys.RANDOM_MAX);
	
	public void tick(int time, Crossing crossing) {
		int counter = trafficManager.getPropertyManager().getCrossingProperty(CrossingProperty.CURRENT_CYCLE_LENGTH, crossing.getId());
		int period = trafficManager.getPropertyManager().getCrossingProperty(CrossingProperty.RANDOM_CYCLE_LENGTH, crossing.getId());		
		if (++counter > period) {
			counter = 0;
			period = random.nextInt(MAX - MIN + 1) + MIN;
			trafficManager.getPropertyManager().setCrossingProperty(CrossingProperty.RANDOM_CYCLE_LENGTH, crossing.getId(), period);
			crossing.changeLights();
		}
		trafficManager.getPropertyManager().setCrossingProperty(CrossingProperty.CURRENT_CYCLE_LENGTH, crossing.getId(), counter);
	}
}