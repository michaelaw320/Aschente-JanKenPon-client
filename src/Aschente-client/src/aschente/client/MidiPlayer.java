/*
 * Copyright (C) 2014 Michael
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package aschente.client;

import java.io.File;
import java.io.IOException;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import javax.sound.midi.Sequencer;
import javax.sound.midi.Synthesizer;

/**
 *
 * @author Michael
 */
public class MidiPlayer {

    private Sequencer sequencer;
    private Synthesizer synthesizer;

    public MidiPlayer() {
        try {
            // the midi file
            int random = (int) (Math.random()*2 + 1);
            File sequence;
            if(random == 1) {
                sequence = new File("Resources\\Sound\\This_game.mid");
            } else {
                sequence = new File("Resources\\Sound\\This game.mid");
            }
            // get the sequencer
            sequencer = MidiSystem.getSequencer(false);
            // get the synthesizer
            synthesizer = MidiSystem.getSynthesizer();
            // load up the instruments into the synthesizer
            synthesizer.loadAllInstruments(synthesizer.getDefaultSoundbank());
            // open the synthesizer
            synthesizer.open();
            // get the synthesizer's receiver
            Receiver receiver = synthesizer.getReceiver();
            // open the sequencer
            sequencer.open();
            // wire up the two
            sequencer.getTransmitter().setReceiver(receiver);
            // set the midi file to play
            sequencer.setSequence(MidiSystem.getSequence(sequence));
            // start the playback
            sequencer.setLoopCount(Sequencer.LOOP_CONTINUOUSLY);
            // set the volume the normal way
            int volume = 127;
            MidiChannel[] channels = synthesizer.getChannels();
            for (MidiChannel channel : channels) {
                channel.controlChange(7, volume);
            }
        } catch (MidiUnavailableException | InvalidMidiDataException | IOException ex) {
            System.err.println(ex);
        }
    }

    public void play() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (sequencer != null) {
                    sequencer.start();
                }
            }
        }).start();
    }
}
