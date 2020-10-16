#include <ncurses.h>

int main()
{
    initscr();

    printw("Hello world!\n");
    // refresh();

    int l, c;
    getmaxyx(stdscr, l, c);

    move(l / 2, c / 2);
    printw("Hello world!\n");
    // printw("Testando...\n");
    refresh();

    getch();
    endwin();

    return 0;
}
