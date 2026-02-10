import tkinter
from tkinter import ttk
from yaml_test import write
from datetime import date



class add_new_data:

    def __init__(self,data_name):

        def is_float(s):
            result = False
            if s.count(".") <= 1:
                if s.replace(".", "").isdigit():
                    result = True
            return result


        #---------------------------------
        #Gui poniżej

        window_add = tkinter.Tk()
        window_add.title("add {0} ".format(data_name))

        today = date.today()

        tkinter.Label(window_add,text="data [Y/M/D]:").grid(column=0, row=0)
        years = []
        for i in range(today.year - 10, today.year + 1):
            years.append(i)

        year = ttk.Combobox(window_add, width=10, state="readonly", value=years)
        year.grid(column=1, row=0, sticky="w")
        year.current(10)


        months = []
        for i in range(1, 13):
            months.append(i)

        month = ttk.Combobox(window_add, width=10, state="readonly", value=months)
        month.grid(column=2, row=0, sticky="w")
        month.current(today.month - 1)


        days = []
        for i in range(1, 32):
            days.append(i)

        day = ttk.Combobox(window_add, width=10, state="readonly", value=days)
        day.grid(column=3, row=0, sticky="w")
        day.current(today.day - 1)



        tkinter.Label(window_add, text="{0}:".format(data_name)).grid(column=0, row=1)
        value = tkinter.Entry(window_add, width=40)
        value.grid(column=1, row=1,columnspan=3)

        tkinter.Label(window_add,text="comment:").grid(column=0, row=2)
        comment = tkinter.Entry(window_add, width=40)
        comment.grid(column=1, row=2,columnspan=3)


        label_result = tkinter.Label(window_add, text="")
        label_result.grid(column=1, row=6,columnspan=4,rowspan=2)

        # Gui powyżej
         #---------------------------------


        #Sprawdzenie wartości i dodanie ich do bazy
        def add_to_data():


            if is_float(value.get()):

                newDate = date.today()
                correctDate = False
                try:
                    newDate = date(int(year.get()),int(month.get()),int(day.get()))
                    correctDate = True
                except ValueError:
                    correctDate = False

                if(correctDate):
                    label_result.config(
                            text='Set: {3} - {0}: {1}, comment: {2}'.format(data_name, value.get(), comment.get(),
                                                                               date(int(year.get()), int(month.get()),
                                                                                    int(day.get()))))
                    new_yaml_data_dict = {
                        date(int(year.get()),int(month.get()),int(day.get())): {
                            data_name: float(value.get()),
                            'comment': comment.get()
                        }
                    }
                    write(new_yaml_data_dict, data_name)
                else:
                    label_result.config(text='Date is uncorrect')

            else:
                label_result.config(text='Value is uncorrect')

        #okno upewniające się co do zamknięcia okna dodania wartosci
        def exit():
            exitsure = tkinter.Toplevel()
            exitsure.title("Exit")

            areyousure = tkinter.Label(exitsure, text="Are you sure you want to exit?")
            areyousure.grid(column=0, row=0, columnspan=4)

            ExitYes = tkinter.Button(exitsure, text="Yes", fg="red",
                                     command=lambda: [window_add.destroy(), exitsure.destroy()])
            ExitYes.grid(column=0, row=2)

            NoYes = tkinter.Button(exitsure, text="No", command=exitsure.destroy)
            NoYes.grid(column=2, row=2)


        #GUI
        tkinter.Button(window_add, text="Add data", command=add_to_data).grid(column=0, row=6)
        tkinter.Button(window_add, text="exit", command=exit, fg="red").grid(column=0, row=7)



        window_add.mainloop()

