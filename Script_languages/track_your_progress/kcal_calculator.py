"""
For men: BMR = 10 x weight (kg) + 6.25 x height (cm) – 5 x age (years) + 5

For women: BMR = 10 x weight (kg) + 6.25 x height (cm) – 5 x age (years) – 161


Sedentary (little or no exercise) : Calorie-Calculation = BMR x 1.2
Lightly active (light exercise/sports 1-3 days/week) : Calorie-Calculation = BMR x 1.375
Moderately active (moderate exercise/sports 3-5 days/week) : Calorie-Calculation = BMR x 1.55
Very active (hard exercise/sports 6-7 days a week) : Calorie-Calculation = BMR x 1.725
If you are extra active (very hard exercise/sports & a physical job) : Calorie-Calculation = BMR x 1.9

"""

import tkinter
from datetime import date
from tkinter import ttk
import yaml_test
from yaml_test import write



class kcal_calculator:

    def __init__(self):


        #Obliczanie i wypisywanie wartości BMR w zaelżności od aktualnie wybranych parametrów
        def calculate(event):

            switch_activity = {0: 1.2, 1: 1.375, 2: 1.55, 3: 1.725, 4: 1.9}

            switch_sex = {0: 5, 1: -161}

            height_a = int(height.get())
            weight_a = int(weight.get()) + int(weight2.get()) / 10
            age_a = int(age.get())

            result = 10 * weight_a + 6.25 * height_a - 5 * age_a + switch_sex.get(sex.current())
            result *= switch_activity.get(activity.current())

            result = round(result, 2)
            label_result.config(text='{:.2f} kcal/day'.format(result))


        #Okno komunikat upewniająca się o decyzji co do zamknięcia kalkulatora
        def exit():
            exitsure = tkinter.Toplevel()

            areyousure = tkinter.Label(exitsure, text="Are you sure you want to exit?")
            areyousure.grid(column=0, row=0, columnspan=4)

            ExitYes = tkinter.Button(exitsure, text="Yes", fg="red",
                                     command=lambda: [window.destroy(), exitsure.destroy()])
            ExitYes.grid(column=0, row=2)

            NoYes = tkinter.Button(exitsure, text="No", command=exitsure.destroy)
            NoYes.grid(column=2, row=2)

        # Obliczanie i zapisuje do grupy 'spalone' kalorie wg aktualnie wybranych parametrów
        def add():

            newDate = date.today()
            correctDate = False
            try:
                newDate = date(int(year.get()), int(month.get()), int(day.get()))
                correctDate = True
            except ValueError:
                correctDate = False

            if (correctDate):
                switch_activity = {0: 1.2, 1: 1.375, 2: 1.55, 3: 1.725, 4: 1.9}

                switch_sex = {0: 5, 1: -161}

                height_a = int(height.get())
                weight_a = int(weight.get()) + int(weight2.get()) / 10
                age_a = int(age.get())

                result = 10 * weight_a + 6.25 * height_a - 5 * age_a + switch_sex.get(sex.current())
                result *= switch_activity.get(activity.current())
                result = round(result, 2)

                user_data = {
                    'height': height_a,
                    'weight': weight_a,
                    'age': age_a,
                    'activity': activity.current(),
                    'sex': sex.current(),
                }

                yaml_test.user(user_data)

                label_result.config(
                    text='Set: {2} - {0}: {1}'.format('kcal', result,
                                                                       date(int(year.get()), int(month.get()),
                                                                            int(day.get()))))
                new_yaml_data_dict = {
                    date(int(year.get()), int(month.get()), int(day.get())): {
                        'kcal': float(result),
                        'comment': ''
                    }
                }
                write(new_yaml_data_dict, 'kcal')


        #-------------------------------------------------------------------------
        #poniżej znajduje się tworzenie GUI


        window = tkinter.Tk()
        window.title("Daily kcal calculator BMR")

        today = date.today()


        tkinter.Label(window, text="date [Y/M/D]:").grid(column=0, row=0)

        years = []
        for i in range(today.year-10, today.year+1):
            years.append(i)

        year = ttk.Combobox(window, width=5, state="readonly", value=years)
        year.grid(column=1, row=0, sticky="w")
        year.current(10)
        year.bind("<<ComboboxSelected>>", calculate)

        months = []
        for i in range(1, 13):
            months.append(i)

        month = ttk.Combobox(window, width=5, state="readonly", value=months)
        month.grid(column=2, row=0,sticky="w")
        month.current(today.month-1)
        month.bind("<<ComboboxSelected>>", calculate)

        days = []
        for i in range(1, 32):
            days.append(i)

        day = ttk.Combobox(window, width=5, state="readonly", value=days)
        day.grid(column=3, row=0,sticky="w")
        day.current(today.day-1)
        day.bind("<<ComboboxSelected>>", calculate)


        tkinter.Label(window,text="height (cm):").grid(column=0, row=1)



        cm = []
        for i in range(100, 251):
            cm.append(i)

        height = ttk.Combobox(window, width=15, state="readonly", value=cm)
        height.grid(column=1, row=1,columnspan=2,sticky="w")
        height.current(0)
        height.bind("<<ComboboxSelected>>", calculate)


        tkinter.Label(window, text="weight (kg):").grid(column=0, row=2)
        kg = []
        for i in range(30, 201):
            kg.append(i)

        kg2 = []
        for i in range(0, 10):
            kg2.append(i)

        weight = ttk.Combobox(window, width=15, state="readonly", value=kg)
        weight.grid(column=1, row=2,sticky="w",columnspan=2)
        weight.current(0)
        weight.bind("<<ComboboxSelected>>", calculate)
        weight2 = ttk.Combobox(window, width=15, state="readonly", value=kg2)
        weight2.grid(column=3, row=2,sticky="w")
        weight2.current(0)
        weight2.bind("<<ComboboxSelected>>", calculate)


        tkinter.Label(window, text="age (years):").grid(column=0, row=3)
        ages = []
        for i in range(16, 101):
            ages.append(i)

        age = ttk.Combobox(window, width=15, state="readonly", value=ages)
        age.grid(column=1, row=3,columnspan=2,sticky="w")
        age.current(0)
        age.bind("<<ComboboxSelected>>", calculate)

        tkinter.Label(window, text="sex:").grid(column=0, row=4)
        sex= ttk.Combobox(window, width=15,state="readonly",values=["Men","Women"])
        sex.grid(column=1, row=4,columnspan=2,sticky="w")
        sex.current(0)
        sex.bind("<<ComboboxSelected>>", calculate)

        tkinter.Label(window, text="activity:").grid(column=0, row=5)
        activity = ttk.Combobox(window, width=35,state="readonly",values=[
            "Little or no exercise [BMI]",
            "Light exercise",
            "Moderate exercise",
            "Hard exercise",
            "Very hard exercise & a physical job"
                                               ])
        activity.grid(column=1, row=5,columnspan=3,sticky="w")
        activity.current(0)
        activity.bind("<<ComboboxSelected>>", calculate)


        label_result = tkinter.Label(window, text="Result")
        label_result.grid(column=1, row=6,columnspan=3)


        tkinter.Button(window, text="Save and add", command=add).grid(column=0, row=6)
        tkinter.Button(window, text="exit", command=exit, fg="red").grid(column=0, row=7)


        # powyżej znajduje się tworzenie GUI
        # -------------------------------------------------------------------------


        #sprawdzenie czy istnieją dane o żytkowniku
        if yaml_test.is_user() is False:
            user_data = {
                'height': int(height.get()),
                'weight': int(weight.get()),
                'age': int(age.get()),
                'activity': int(activity.current()),
                'sex': int(sex.current()),
            }
            yaml_test.user(user_data)



        #Ustawienie startowych wartości na dane użytkownika
        data_user_y = yaml_test.get_user()

        height.set(int(data_user_y.get('height')))
        weight.set(int(data_user_y.get('weight')//1))
        test = int((data_user_y.get('weight')*10)%10)
        weight2.set(test)
        age.set(data_user_y.get('age'))
        activity.current(data_user_y.get('activity'))
        sex.current(data_user_y.get('sex'))

        calculate(self)



        window.mainloop()

