#include <ncurses.h>

int main()
{
    initscr();

    int numLinhas, numColunas, y0, x0, y1, x1, y2, x2, y3, x3;

    getmaxyx(stdscr, numLinhas, numColunas);

    y0 = 0, x0 = 0;
    y1 = numLinhas, x1 = (numColunas / 2) - 1;

    y2 = 0, x2 = (numColunas / 2) + 1;
    y3 = numLinhas, x3 = (numColunas / 2) - 1;

    WINDOW *win1 = newwin(y1, x1, y0, x0);
    WINDOW *win2 = newwin(y3, x3, y2, x2);
    refresh();

    box(win1, '*', '*');
    box(win2, '*', '*');

    wrefresh(win1);
    wrefresh(win2);

    mvwprintw(win2, 0, 0, "Janela 2");

    getch();
    endwin();

    return 0;
}
