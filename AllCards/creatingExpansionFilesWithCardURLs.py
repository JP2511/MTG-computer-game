#open a file
def openFile(path):
    with open("temps\\" + path) as datafile:
        data = datafile.read()
    return data

#return the name of the expansion
def getNameOfExpansion(data):
    name = data.split("<title>")[1].split(" |")[0].replace(" ","")
    if "Spoiler" in name:
        name = name.split("Spoiler")[0]
    if "Visual" in name:
        name = name.split("Visual")[0]
    if name == "m12":
        name = "Magic2012"
    if name == "M14":
        name = "Magic2014"
    if ":" in name:
        name = name.replace(":",";")
    return name.title()

#reduce the content of the html page
def reduceHTML(data):
    return data.split("<!--CONTENT-->")[32].split("<!--END CONTENT-->")[0].splitlines()

#get only the links
def getLinks(data, name):
    links = []
    for i in data:
        if i.startswith('<a '):
            if "cards" in i:
                if name.split(".txt")[0] == "ccon":
                    expansion = "con"
                else:
                    expansion = name.split(".txt")[0]
                links.append("http://mythicspoiler.com/" + expansion + "/" + i.split('ref="')[1].split('"')[0])
    return links

#write files
def writeFile(folder, name, URLs):
    file = open(folder + name + ".txt", "w+")
    file.write("_".join(URLs) + "_n")
    file.close()

#parsing the html of each expansion and creating a text file with the name of the expansion and with the links to all the cards of each expansion
main_file = openFile("allExpansionsFileNames.txt")
all_expansions = []
for i in main_file.split("_")[:-1]:
    expansion = openFile(i)
    title_of_expansion = getNameOfExpansion(expansion)
    if title_of_expansion.startswith("Commander"):
        all_expansions.append("commanderDecks/" + title_of_expansion)
    else:
        all_expansions.append("expansions/" + title_of_expansion)
    reduced_HTML_page = reduceHTML(expansion)
    links = getLinks(reduced_HTML_page, i.split(".txt")[0])
    writeFile("temps\\", title_of_expansion, links)
    writeFile("", "allExpansions", all_expansions)