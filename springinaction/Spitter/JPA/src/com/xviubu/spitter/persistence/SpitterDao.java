package com.xviubu.spitter.persistence;

import com.xviubu.spitter.model.*;
import java.util.*;

public interface SpitterDao
{
	public void addSpitter(Spitter spitter);
	public void saveSpitter(Spitter spitter);
	public Spitter getSpitterById(long id);
}
