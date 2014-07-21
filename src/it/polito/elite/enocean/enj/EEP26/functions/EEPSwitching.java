/*
 * EnJ - EnOcean Java API
 * 
 * Copyright 2014 Andrea Biasi, Dario Bonino 
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */
package it.polito.elite.enocean.enj.EEP26.functions;

import it.polito.elite.enocean.enj.EEP26.D2.D201.D201;
import it.polito.elite.enocean.enj.EEP26.D2.D201.D20108;

/**
 * A class representing the switching function associated to some of the EEPs
 * defined in the 2.6 specification.
 * 
 * @author bonino
 *
 */
public class EEPSwitching extends EEPFunction
{

	// the EEPFunction name
	public static final String NAME = "Switching";

	// the human readable values
	public static final boolean ON = true;
	public static final boolean OFF = false;

	/**
	 * Basic constructor, builds a new {@link EEPSwitching} instance initialized
	 * at {@link EEPFunction}.OFF.
	 */
	public EEPSwitching()
	{
		// call the super class constructor
		super(EEPSwitching.NAME);

		// store supported EEPs
		this.supportedEEP.add(D201.class.getSimpleName());
		this.supportedEEP.add(D20108.class.getSimpleName());

		// by default the function starts at OFF
		this.value = EEPSwitching.OFF;
	}

	/**
	 * Constructor, builds a new {@link EEPSwitching} instance with the given
	 * value (true == on, false == off).
	 * 
	 * @param value
	 */
	public EEPSwitching(boolean value)
	{
		// call the super class constructor
		super(EEPSwitching.NAME);

		// store supported EEPs
		this.supportedEEP.add(D201.class.getSimpleName());
		this.supportedEEP.add(D20108.class.getSimpleName());

		// by default the function starts at OFF
		this.value = value;
	}

	@Override
	public Object getValue()
	{
		return value;
	}

	@Override
	public boolean setValue(Object value)
	{
		boolean stored = false;

		if (value instanceof Boolean)
		{
			// store the current value
			this.value = value;

			// updated the operation result
			stored = true;
		}

		return stored;

	}

	@Override
	public String getUnit()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setUnit()
	{
		// intentionally left empty, no unit is supported.
	}

}