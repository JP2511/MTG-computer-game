library(readr)
library(tidyverse)
library(comprehenr)

#getting the data and cleaning it out
cardsInformation <- read_delim("Java/MTG/AllCards/cardsInformation.txt", "_", escape_double = FALSE, col_names = FALSE, trim_ws = TRUE)
colnames(cardsInformation) <- c("Name", "Mana", "Type", "SubType", "Effect", "Power", "Life")
cardsInformation$Name <- str_replace_all(cardsInformation$Name, "%", " ")

#just getting the instants
instants <- cardsInformation %>% filter(Type == "Instant") %>% select(-c(SubType, Power, Life))

##obtaining all information about counter spells
counterSpells <- instants %>% filter(str_detect(instants$Effect, ".*[cC]ounter target(.*)spell.*"))
counterSpells <- counterSpells %>% mutate(CounterWhat = str_replace(counterSpells$Effect, ".*[cC]ounter target(.*)spell.*", "\\1") %>% 
                                            str_trim(), Unless = Effect)
counterSpells$Unless <- ifelse(str_detect(counterSpells$Unless, ".*[uU]nless.*"), str_replace(counterSpells$Unless, ".*[uU]nless(.*)","\\1"), 
                               str_replace(counterSpells$Unless, ".*", "")) %>% str_trim()
counterSpells$CounterWhat <- str_replace(counterSpells$CounterWhat,".*spell.*", "")
g <- str_split_fixed(counterSpells$Unless, "\\.",2)
colnames(g) <- c("Unless", "Rest")
counterSpells <- counterSpells %>% select(-Unless) %>% mutate(Unless = g[,1], Rest = g[,2])
counterSpells$Rest <- str_replace_all(counterSpells$Rest, "@", "")
counterSpellsEffect <- str_split_fixed(counterSpells$Effect, "\\.", 3)
######View(counterSpells)

#obtaining all cards that can't be countered  
cantBeCountered <- cardsInformation %>% filter(str_detect(cardsInformation$Effect, ".*can't be countered.*")) %>% distinct()
cantBeCountered <- cantBeCountered %>%  filter(!str_detect(cantBeCountered$Effect, ".*[Tt]his spell can't be countered"))
a <- to_vec(for(i in 1:length(cantBeCountered[[1]])) str_detect(cantBeCountered[[5]][i], 
                                                                paste0(".*\"?", cantBeCountered[[1]][i] %>% str_trim(), "\"? can't be countered.*")))
cantBeCountered <- cantBeCountered %>% filter(!a) #we take out all that abide by the format ".*[iI]f.*{name} can't be countered.*" and ".*\"?{name}\"? can't be countered"
View(cantBeCountered)

#obtaining cards that have '[name] enters the battlefield tapped'
entersTapped <- cardsInformation %>% filter(Type != "Instant" && Type != "sorcery" && Type != "Enchantment") %>% 
  filter(str_detect(Effect, ".* enters the battlefield tapped"))
######View(entersTapped)

#obtaining cards that have Cycling effect
cyclingCards <- cardsInformation %>% filter(str_detect(cardsInformation$Effect, "Cycling")) %>% mutate(Cycling = Effect)
cyclingCards <- cyclingCards %>% filter(!str_detect(cyclingCards$Cycling, "Cycling abilities"))
cyclingCards$Cycling <- str_replace(cyclingCards$Cycling, ".*Cycling (.*)", "\\1") %>% str_trim()
cyclingCards$Cycling <- str_split_fixed(cyclingCards$Cycling, "<i>", 2)[,1] 
cyclingCards$Cycling <- str_split_fixed(cyclingCards$Cycling, "<br>",2)[,1]
#####View(cyclingCards %>% filter(Type == "Land") %>% select(-c(Mana, Power, Life)))

