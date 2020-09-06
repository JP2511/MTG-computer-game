#opens the specified file
def openFile(path):
    with open(path, encoding = "utf8") as datafile:
        data = datafile.read()
    return data

#reduces the size of the html page to the bare necessities
def reduceHTML(data):
    if len(data) == 0:
        reduced_html = ""
    elif "<!--CONTENT-->" in data:
        reduced_html = data.split("<!--CONTENT-->")[32].split("<!--END CONTENT-->")[0]
    else:
        reduced_html = ""
    return reduced_html

#returns the name of the card
def getCardName(data):
    if len(data) == 0:
        name = ""
    elif "<!--CARD NAME-->" not in data:
        name = ""
    else:
        name = data.split("<!--CARD NAME-->")[1].split("</font")[0].strip().replace(" ","%")
    return name

#returns the mana cost of the card
def getManaCost(data):
    if len(data) == 0:
        manaCost = ""
    elif "<!--MANA COST-->" not in data:
       manaCost = ""
    else:
       manaCost = data.split("<!--MANA COST-->")[1].split("</td")[0].strip()
    return manaCost

#returns the type and subtype of the card together
def getTypeAndSubtype(data):
    if len(data) == 0:
        type_and_subtype = ""
    elif "<!--TYPE-->" not in data:
        type_and_subtype = ""
    else:
        type_and_subtype = data.split("<!--TYPE-->")[1].split("</td")[0].strip()
    return type_and_subtype

#returns the type of the card
def getType(data):
    if "-" not in data:
        type_of_card = data
    else:
        type_of_card = data.split("-")[0].strip()
        if "Legendary" in type_of_card:
            type_of_card = type_of_card.split("Legendary ")[1]
    return type_of_card

#returns the subtype of the card
def getSubType(data):
    if "-" not in data:
        subType = ""
    else:
        subType = data.split("-")[1].strip()
    return subType

#get the card's effects
def getEffect(data):
    if len(data) == 0:
        effect = ""
    elif "<!--CARD TEXT-->" not in data:
        effect = ""
    elif "<!--TYPE-->" not in data and "<!--CARD NAME-->" in data:
        if "<!--CARD TEXT-->" not in data.split("<!--CARD NAME-->")[1]:
            effect = ""
        else:
            effect = data.split("<!--CARD NAME-->")[1].split("<!--CARD TEXT-->")[1].split("</td")[0].strip().replace("<br />","@").replace("\n","")
    elif "<!--TYPE-->" in data:
         if "<!--CARD TEXT-->" not in data.split("<!--TYPE-->")[1]:
             effect = ""
         else:
             effect = data.split("<!--TYPE-->")[1].split("<!--CARD TEXT-->")[1].split("</td")[0].strip().replace("<br />","@").replace("\n","")
    else:
        effect = ""
    return effect

#return power and life of a card if it has any
def getPowerAndLife(data):
    if len(data) == 0:
        power_and_life = ""
    elif "<!--P/T-->" not in data:
        power_and_life = ""
    else:
        power_and_life = data.split("<!--P/T-->")[1].split("<!--END CARD TEXT-->")[0].strip()
    return power_and_life

#returns the power of a creature card
def getPower(data):
    if "/" not in data:
        power = ""
    else:
        power = data.split("/")[0]
    return power

#returs the life of a creature card
def getLife(data):
    if "/" not in data:
        if "[" in data:
            life = data.split("[")[1].split("]")[0]
        else:
            life = ""
    else:
        life = data.split("/")[1]
    return life

#write files with information about all cards
def writeFile(path, list_of_cards):
    file = open("final\\" + path, "w+")
    for i in list_of_cards:
        if len(i.strip()) != 0:
            file.write(i + "\n")
    file.close()

expansion_names = openFile("allExpansionNames.txt").splitlines()
cards_names_all_expansions = openFile("cardsNames.txt").splitlines()
for i in expansion_names:
    expansion_all_cards = []
    for j in cards_names_all_expansions:
        card_information_line = ""
        if j.startswith(i.split(".")[0]):
            card_information = []
            try:
                file = openFile("temps\\" + j)
            except:
                next
            html = reduceHTML(file)
            card_information.append(getCardName(html))
            card_information.append(getManaCost(html))
            type_and_subtype = getTypeAndSubtype(html)
            card_information.append(getType(type_and_subtype))
            card_information.append(getSubType(type_and_subtype))
            card_information.append(getEffect(html))
            power_and_life_of_card = getPowerAndLife(html)
            card_information.append(getPower(power_and_life_of_card))
            card_information.append(getLife(power_and_life_of_card))
            card_information_line = "_".join(card_information)
        if not card_information_line.startswith("<!--END%CARD%TEXT-->") and card_information_line != "____@__" and card_information_line != "______" and len(card_information_line) > 0:
            expansion_all_cards.append(card_information_line)
    if len(expansion_all_cards) > 0:
        writeFile(i, expansion_all_cards)