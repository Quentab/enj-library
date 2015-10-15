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
package it.polito.elite.enocean.enj.eep.eep26.attributes;

import java.nio.ByteBuffer;

import it.polito.elite.enocean.enj.eep.EEPAttribute;

/**
 * @author bonino
 *
 */
public class EEP26HumidityLinear extends EEPAttribute<Double>
{
	// the EEPFunction name
	public static final String NAME = "RelativeHumidity";
	public static final double MAX_VALID_RAW = 250.0;

	// the allowed range
	private double minH;
	private double maxH;

	/**
	 * @param name
	 */
	public EEP26HumidityLinear()
	{
		super(EEP26HumidityLinear.NAME);

		// default value= -273 °C
		this.value = 0.0;
		this.unit = "%";
		this.minH = 0.0;
		this.maxH = Double.MAX_VALUE;
	}

	public EEP26HumidityLinear(Double value, String unit)
	{
		super(EEP26HumidityLinear.NAME);

		if ((unit != null) && (value != null) && (!unit.isEmpty())
				&& (unit.equalsIgnoreCase("%")))
		{
			// store the value
			this.value = value;

			// store the unit
			this.unit = unit;

			// set the maximum range
			this.minH = 0.0;
			this.maxH = Double.MAX_VALUE;
		}

		else
		{
			throw new NumberFormatException(
					"Wrong unit or null value for relative humidity expressed in %");
		}

	}

	public EEP26HumidityLinear(Double minH, Double maxH)
	{
		super(EEP26HumidityLinear.NAME);

		// default value= -273 °C
		this.value = 0.0;
		this.unit = "Celsius";
		this.minH = minH;
		this.maxH = maxH;
	}

	/**
	 * @return the minH
	 */
	public double getMinH()
	{
		return minH;
	}

	/**
	 * @param minH
	 *            the minH to set
	 */
	public void setMinH(double minH)
	{
		this.minH = minH;
	}

	/**
	 * @return the maxH
	 */
	public double getMaxH()
	{
		return maxH;
	}

	/**
	 * @param maxH
	 *            the maxH to set
	 */
	public void setMaxH(double maxH)
	{
		this.maxH = maxH;
	}

	/*
	 * @see it.polito.elite.enocean.enj.eep.EEPAttribute#setValue
	 */
	@Override
	public boolean setValue(Double value)
	{
		boolean stored = false;

		if (value instanceof Number)
		{
			// store the current value
			this.value = value;

			// updated the operation result
			stored = true;
		}

		return stored;
	}

	public void setRawValue(int value)
	{
		// perform the scaling
		// TODO check conversion
		this.value = ((this.maxH - this.minH) * ((double) (value)))
				/ EEP26HumidityLinear.MAX_VALID_RAW + this.minH;
	}

	/*
	 * @see it.polito.elite.enocean.enj.eep.EEPAttribute#setUnit
	 */
	@Override
	public boolean setUnit(String unit)
	{
		boolean stored = false;

		if ((unit != null)

		&& (!unit.isEmpty()) && (unit.equalsIgnoreCase("%")))
		{

			// store the unit
			this.unit = unit;

			// set the stored flag at true
			stored = true;
		}

		return stored;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see it.polito.elite.enocean.enj.eep.EEPAttribute#byteValue()
	 */
	@Override
	public byte[] byteValue()
	{
		// it is likely to never be used...

		// use byte buffers to ease double encoding / decoding

		// a byte buffer wrapping an array of 4 bytes
		ByteBuffer valueAsBytes = ByteBuffer.wrap(new byte[4]);

		// store the current value
		valueAsBytes.putDouble(this.value);

		// return the value as byte array
		return valueAsBytes.array();
	}

	/**
	 * Checks if the current attribute represents a value in the declared valid
	 * range or not.
	 * 
	 * @return
	 */
	public boolean isValid()
	{
		return ((this.value >= this.minH) && (this.value <= this.maxH));
	}

}
