package com.springinaction.springidol;
import com.springinaction.springidol.qualifiers.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Component;
import javax.inject.Inject;
import javax.inject.Named;

//@Component("kenny")
public class Instrumentalist implements Performer
{
	public Instrumentalist()
	{

	}
	public void perform() throws PerformanceException
	{
		System.out.println("Playing " + song + " :" );
		instrument.play();
	}

	public void setSong(String song)
	{
		this.song = song;
	}

	public String getSong()
	{
		return song;
	}

	public String screamSong()
	{
		return song;
	}
	
	public void setInstrument(Instrument instrument)
	{
		this.instrument = instrument;
	}


//	@Autowired
//	@Qualifier("saxophone") //注入ID 为saxophone的Bean
//	@Qualifier("stringed")
//	@StringedInstrument
//	@Strummed
//	@Inject
//	@Named("saxophone")
//	@StringedInstrument
//	@Strummed
	private Instrument instrument;
//  @Value("电台情歌")
	private String song;
}
