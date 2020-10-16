#include <ncurses.h>

int main()
{
    initscr();

    int numLinhas, numColunas, y0, x0, y1, x1;

    getmaxyx(stdscr, numLinhas, numColunas);
    y0 = 0, x0 = 0;
    y1 = numLinhas, x1 = (numColunas / 2) - 1;

    WINDOW *win1 = newwin(y1, x1, y0, x0);
    refresh();
    box(win1, '*', '*');
    wrefresh(win1);

    mvwprintw(win1, 1, 1, "Hello world!");
    wrefresh(win1);

    getch();
    endwin();

    return 0;
}
