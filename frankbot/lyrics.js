const Discord = require('discord.js');
const client = new Discord.Client();
const lyricsFinder = require('lyrics-finder');

const prefix = '!'; // Customize your bot's prefix here

client.on('ready', () => {
  console.log(`Logged in as ${client.user.tag}`);
});

client.on('message', async (message) => {
  if (!message.content.startsWith(prefix) || message.author.bot) return;

  const args = message.content.slice(prefix.length).trim().split(' ');
  const command = args.shift().toLowerCase();

  if (command === 'lyrics') {
    if (!args.length) {
      return message.reply('Please provide a song name!');
    }

    const query = args.join(' ');

    try {
      const lyrics = await lyricsFinder(query);
      if (!lyrics) {
        message.channel.send(`No lyrics found for "${query}".`);
      } else {
        message.channel.send(`Lyrics for "${query}":\n\n${lyrics}`);
      }
    } catch (error) {
      console.error(error);
      message.channel.send('An error occurred while searching for lyrics.');
    }
  }
});

// Replace 'YOUR_DISCORD_BOT_TOKEN' with your actual bot token
client.login('YMTEwNjMyMzE5MTAxNTY2OTc5MA.G2wu-u.KLyPe5Rf7hm-OU5_VOglF0woEIndvylFPEk42g');
