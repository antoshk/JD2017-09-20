package by.it.akhmelev.project.java.controller;

enum Actions {

    INDEX {
        {
            this.command = new CmdIndex();
        }
    },
    LOGIN {
        {
            this.command = new CmdLogin();
        }
    },
    SIGNUP {
        {
            this.command = new CmdSignup();
        }
    },
    PROFILE{
        {
            this.command = new CmdProfile();
        }
    },
    RESETDB{
        {
            this.command = new CmdResetDB();
        }
    },
    CREATEAD{
        {
            this.command = new CmdCreateAd();
        }
    },
    ERROR{
        {
            this.command = new CmdError();
        }
    };

    ICommand command;
}
