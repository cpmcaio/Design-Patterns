package Examples;
import java.util.ArrayList;
import java.util.List;

public class State1 {

    public static class Player {
        private StateImp currentState;
        private boolean playing;
        List<String> playlist = new ArrayList<>();
        int currentTrackIndex = 0;

        public Player() {
            this.currentState = new Ready(this);
            this.playing = false;
            playlist.add("Song 1");
            playlist.add("Song 2");
            playlist.add("Song 3");
        }

        public void setState(StateImp state) { this.currentState = state; }
        public void setPlaying(boolean playing) { this.playing = playing; }
        public boolean isPlaying() { return this.playing; }

        public String startPlayback() { return playlist.get(currentTrackIndex); }

        public String nextTrack() {
            currentTrackIndex = (currentTrackIndex + 1) % playlist.size();
            return playlist.get(currentTrackIndex);
        }

        public String previousTrack() {
            currentTrackIndex = (currentTrackIndex - 1 + playlist.size()) % playlist.size();
            return playlist.get(currentTrackIndex);
        }

        public void play()     { currentState.play(); }
        public void stop()     { currentState.stop(); }
        public void next()     { currentState.next(); }
        public void previous() { currentState.previous(); }
        public void lock()     { currentState.lock(); }
    }

    public static abstract class StateImp {
        protected Player player;

        public StateImp(Player player) { this.player = player; }

        public abstract void play();
        public abstract void stop();
        public abstract void next();
        public abstract void previous();
        public abstract void lock();
    }

    public static class Ready extends StateImp {
        public Ready(Player player) { super(player); }

        @Override
        public void play() {
            System.out.println("Playing: " + player.startPlayback());
            player.setPlaying(true);
            player.setState(new Playing(player));
        }

        @Override
        public void stop() { System.out.println("Already stopped."); }

        @Override
        public void next() { System.out.println("Next: " + player.nextTrack()); }

        @Override
        public void previous() { System.out.println("Previous: " + player.previousTrack()); }

        @Override
        public void lock() {
            player.setState(new Locked(player));
            System.out.println("Player locked.");
        }
    }

    public static class Playing extends StateImp {
        public Playing(Player player) { super(player); }

        @Override
        public void play() {
            System.out.println("Pausing...");
            player.setPlaying(false);
            player.setState(new Ready(player));
        }

        @Override
        public void stop() {
            System.out.println("Stopping...");
            player.setPlaying(false);
            player.setState(new Ready(player));
        }

        @Override
        public void next() { System.out.println("Next: " + player.nextTrack()); }

        @Override
        public void previous() { System.out.println("Previous: " + player.previousTrack()); }

        @Override
        public void lock() {
            player.setState(new Locked(player));
            System.out.println("Player locked while playing.");
        }
    }

    public static class Locked extends StateImp {
        public Locked(Player player) { super(player); }

        @Override
        public void play() { System.out.println("Player is locked. Unlock first."); }

        @Override
        public void stop() { System.out.println("Player is locked. Unlock first."); }

        @Override
        public void next() { System.out.println("Player is locked. Unlock first."); }

        @Override
        public void previous() { System.out.println("Player is locked. Unlock first."); }

        @Override
        public void lock() {
            player.setState(new Ready(player));
            System.out.println("Player unlocked.");
        }
    }

    public static void main(String[] args) {
        Player player = new Player();
        player.play();
        player.next();
        player.stop();
        player.lock();
        player.play();
        player.lock();
        player.play();
    }
}
