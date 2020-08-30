#open a file
def openFile(path):
    with open(path) as datafile:
        data = datafile.read().splitlines()
    return data

#reduce page content
def reduceHTML(data):
    reduced_HTML = []
    j = 0
    for i in data:
        if i.startswith("<!--CONTENT-->"):
            j = 1
        if i.startswith("<!--END CONTENT-->"):
            break
        if j == 1:
            reduced_HTML.append(i)
    return reduced_HTML
            
#get the link tags
def getCompleteLinks(data):
    links = []
    for i in data:
        if i.startswith("<a ") and "http://mythicalspoiler.com/" + i.split('"')[1] not in links:
            links.append("http://mythicalspoiler.com/" + i.split('"')[1])
    return links

#get the names of the files
def getNamesOfFiles(data):
    names = []
    for i in data:
        if i.split("/")[3] == "con":
            names.append("ccon.txt")
        else:
            names.append(i.split("/")[3]+".txt")
    return names

#create a file with the elements of a list concatenated and an added meaningless element
def createFiles(folder, name, list_of_names_or_URLs):
    file = open(folder + "\\" + name, "w+")
    file.write("_".join(list_of_names_or_URLs) + "_n")
    file.close()
    
#applying the functions to the mainpage.txt
mainpage = openFile("mainpage.txt")
reduced_mainpage = reduceHTML(mainpage)
complete_links = getCompleteLinks(reduced_mainpage)
names_of_files = getNamesOfFiles(complete_links)
createFiles("temps", "allExpansionsURLs.txt", complete_links)
createFiles("temps", "allExpansionsFileNames.txt", names_of_files)