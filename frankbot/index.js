const{Client, GatewayIntentBits, Partials, SystemChannelFlagsBitField} = require("discord.js");
const config = require('./config.json');
const axios = require('axios');
const fs = require("fs");
const prefix = '!';
const musixmatchApiKey = '80fb3e93fe268cc8dea0ae3e2610e8e5';

const client = new Client({
    intents: [Object.keys(GatewayIntentBits)],
    partials: [Object.keys(Partials)]
})
client.on('messageCreate', async message =>{
    if(message.content == 'frank'){
        message.channel.send({content: "bad"})
    }
})


const data = fs.readFileSync("./frankbot.json", "utf8");
const frankbot = JSON.parse(data);
console.log(frankbot);

if (!frankbot.frank) {
  frankbot.frank = 0;
}


client.on('messageCreate', async message =>{
    if(message.content.includes("rizz") && (!message.author.bot)){
        frankbot.frank += 1;
        fs.writeFileSync("./frankbot.json", JSON.stringify(frankbot));
        message.channel.send({content: "Frank's rizz is " + frankbot.frank})
    }
})

client.on('messageCreate', async (message) => {
  if(message.content == "!frankreset"){
    frankbot.frank = 0;
    fs.writeFileSync("./frankbot.json", JSON.stringify(frankbot));
    message.channel.send({content: "Frank's rizz has been reset"})
  }
})

client.on('messageCreate', async message =>{
    if(message.content == 'test'){
        message.channel.send({content: fs.test})
    }
})

client.on('messageCreate', async (message) => {
  if (!message.content.startsWith(prefix) || message.author.bot) return;

  const args = message.content.slice(prefix.length).trim().split(' ');
  const command = args.shift().toLowerCase();

  if (command === 'lyrics') {
    if (!args.length) {
      return message.reply('Please provide a song name!');
    }

    const query = args.join(' ');
    console.log(query);

    try {
      const url = `https://api.musixmatch.com/ws/1.1/track.search?q_track=${encodeURIComponent(
        query
      )}&apikey=${musixmatchApiKey}&f_has_lyrics=true`;
      console.log(url);
      const response = await axios.get(
        url
      );
       // console.log(response);
      const trackList = response.data.message.body.track_list;
      if (trackList.length === 0) {
        return message.channel.send(`No lyrics found for "${query}".`);
      }

      const trackId = trackList[0].track.track_id;
      console.log(trackId);

      let trackurl = `https://api.musixmatch.com/ws/1.1/track.lyrics.get?track_id=${trackId}&apikey=${musixmatchApiKey}`;
      console.log(trackurl);
      const lyricsResponse = await axios.get(
        trackurl
      );
      // console.log(lyricsResponse);
      const lyrics = lyricsResponse.data.message.body.lyrics.lyrics_body;
      if (!lyrics) {
        message.channel.send(`No lyrics found for "${query}".`);
      } else {
        // Cleaning up the lyrics by removing unwanted lines
        let cleanedLyrics = lyrics
        .replace(/\n\.+/, "") // replace the "..." line
        .replace(/\n\*+ This Lyrics is NOT for Commercial use \*+/, "") // replace the disclaimer
        .replace(/\n\(\d+\)/, "") // replace the ending number
        .trim();
        message.channel.send(`Lyrics for "${query}":\n\n${cleanedLyrics}`);
      }
    } catch (error) {
      console.error(error);
      message.channel.send('An error occurred while searching for lyrics.');
    }
  }
});


client.login(config.token).then(()=>{
    console.log('Frank is ready to rizz');
 })
