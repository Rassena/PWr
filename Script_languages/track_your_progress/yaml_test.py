import os
import yaml

#Funkcja tworząca i dodająca nową wartość do danej grupy danech
def write(new_yaml_data_dict,data_name):

    if not os.path.isfile("data/{0}.yaml".format(data_name)):

        with open("data/{0}.yaml".format(data_name), "a") as fo:
            fo.write("")

    sdump = yaml.dump(
                new_yaml_data_dict
                )

    with open("data/{0}.yaml".format(data_name), "a") as fo:
        fo.write(sdump)

#Nadpisanie danych użytkownika
def user(user_data):
    with open("user.yaml", "w") as fo:
        fo.write(yaml.dump(user_data))

#zwrócenie danych o użytkowniku
def get_user():
    if not os.path.isfile("user.yaml"):

        with open("user.yaml", "a") as fo:
            fo.write("")

    with open("user.yaml", "r") as fo:
        documents = yaml.full_load(fo)
        return documents

#Sprawdzenie i stworzenie pliku zawierającego dane o użytkowniku
def is_user():
    isUser = False
    if not os.path.isfile("user.yaml"):
        with open("user.yaml", "a") as fo:
            fo.write("")
    else:
        isUser =True
    return isUser


#zwrócenie danych z wybranej grupy
def get_yaml(data_name):

    with open(r'data/{0}.yaml'.format(data_name)) as file:
        documents = yaml.full_load(file)
    return documents

#Sprawdzenie i stworzenie folderu z pierwszą grupą danych
def ensure_dir(file_path):
    directory = os.path.dirname(file_path)
    if not os.path.exists(directory):
        os.makedirs(directory)
        with open("data/{0}.yaml".format('weight'), "a") as fo:
            fo.write("")