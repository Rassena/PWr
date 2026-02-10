import tkinter

import matplotlib.pyplot as plt
from matplotlib.dates import (YEARLY, DateFormatter, rrulewrapper)
import numpy as np


from matplotlib.backends._backend_tk import NavigationToolbar2Tk
from matplotlib.backends.backend_tkagg import FigureCanvasTkAgg

import yaml_test


class graph_from_yaml:

    def __init__(self,data_name):

        formatter = DateFormatter('%Y-%m-%d')

        dates_y = []
        values_y =[]

        data_from_yaml = yaml_test.get_yaml(data_name)

        #Sprawdzenie czy są jakieś wartości w danej grupie i stworzenie wykresu
        if data_from_yaml is not None:

            for key in sorted(data_from_yaml):
                dates_y = np.append(dates_y, key)
                values_y = np.append(values_y, (float)(data_from_yaml[key].get(data_name)))

            fig, ax = plt.subplots()
            plt.plot_date(dates_y, values_y, color='green', linewidth=1, marker='o',linestyle='dashed', markerfacecolor='blue', markersize=6)
            plt.ylabel(data_name)
            ax.xaxis.set_major_formatter(formatter)

            root = tkinter.Tk()
            root.wm_title(data_name)

            canvas = FigureCanvasTkAgg(fig, master=root)
            canvas.draw()
            canvas.get_tk_widget().pack(side=tkinter.TOP, fill=tkinter.BOTH, expand=1)

            toolbar = NavigationToolbar2Tk(canvas, root)
            toolbar.update()
            canvas.get_tk_widget().pack(side=tkinter.TOP, fill=tkinter.BOTH, expand=1)

            fig.autofmt_xdate()

        #przypadek gdy grupa nie ma żadnych wartości
        else:
            window_s = tkinter.Tk()
            window_s.title(data_name)
            label_result = tkinter.Label(window_s, text="No data to show")
            label_result.grid(column=0, row=0)
            tkinter.Button(window_s, text="exit", command=window_s.destroy, fg="red").grid(column=0, row=1)

        tkinter.mainloop()


