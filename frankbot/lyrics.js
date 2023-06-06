const Discord = require('discord.js');
const client = new Discord.Client();
const lyricsFinder = require('axios');

const prefix = '!'; // Customize your bot's prefix here

client.on('ready', () => {
  console.log(`Logged in as ${client.user.tag}`);
});

client.on('message', async (message) => {
  if (!message.content.startsWith(prefix) || message.author.bot) return;

  const args = message.content.slice(prefix.length).trim().split(' ');
  const command = args.shift().toLowerCase();

  if (command === 'lyrics') {
    if (args.length < 2) {
      return message.reply('Please provide both the song name and artist!');
    }
    const query = args.join(' ');
    let artist = args[args.length - 1];
    artist = artist.get(args);

    try {
      const lyrics = await lyricsFinder(query, artist);
      if (!lyrics) {
        message.channel.send(`No lyrics found for "${query}" by "${artist}".`);
      } else {
        message.channel.send(`Lyrics for "${query}" by "${artist}":\n\n${lyrics}`);
      }
    } catch (error) {
      console.error(error);
      message.channel.send('An error occurred while searching for lyrics.');
    }
  }
});

client.login('MTEwNjMyMzE5MTAxNTY2OTc5MA.GblwpD.xG-pvzjyy8O9QDr2urIdJoj0Lk-uDS1ceEJHsY');
